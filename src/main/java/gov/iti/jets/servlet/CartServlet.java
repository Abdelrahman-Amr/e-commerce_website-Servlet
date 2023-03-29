package gov.iti.jets.servlet;


import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.service.OrderMasterService;
import gov.iti.jets.service.ProductService;
import gov.iti.jets.service.SizeService;
import gov.iti.jets.util.Constants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {

    ProductService productService;
    ProductMapper productMapper;
    SizeService sizeService;
    OrderMasterService orderMasterService;


    @Override
    public void init()
    {
        productService = ProductService.getInstance();
        sizeService =  SizeService.getInstance();
        productMapper = Mappers.getMapper(ProductMapper.class);
        orderMasterService = OrderMasterService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(req.getSession(false));

        response.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/checkout.jsp");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/footer.jsp");
        rd.include(req, response);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        long productId = Long.parseLong(req.getParameter("pdId"));
        String sizeName= req.getParameter("sizeName");
        int op= Integer.parseInt(req.getParameter("op"));

        SizeDto sizeDto = sizeService.getSizeByName(sizeName);
        ProductDto productDto = productService.getProductById(productId);
        HttpSession session =  req.getSession(false);
        if(session!=null && session.getAttribute("cart")==null)
        {
            session.setAttribute("cart", new ArrayList<>());
        }
        List<OrderDetailDto> cart = (List<OrderDetailDto>) session.getAttribute("cart");
        OrderDetailDto order =  orderMasterService.searchProduct(cart, productId, sizeDto.getName()) ;

            if(order == null)
            {

                orderMasterService.createOrderDetail(productDto, sizeDto, cart);

            }
            else
            {
                orderMasterService.updateOrderDetail(productDto, sizeDto, order, op);
                if(order.getQuantity()==0)
                {
                    cart.remove(order);
                }
            }

        CustomerDto customerDto = (CustomerDto) session.getAttribute("customer");
        if(cart.size() == 0 && customerDto!=null)
        {
            orderMasterService.removeCart(customerDto.getId());
        }
        session.setAttribute("cart", cart);
        session.setAttribute("cartTotal", orderMasterService.calcCartTotal(cart));
        session.setAttribute("cartSize", orderMasterService.calcCartSize(cart));
        session.setAttribute("dev", Constants.Dev);

    }

}
