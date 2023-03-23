package gov.iti.jets.servlet;


import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.mapper.ProductMapper;
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


    @Override
    public void init()
    {
        productService = new ProductService();
        sizeService =  SizeService.getInstance();
        productMapper = Mappers.getMapper(ProductMapper.class);
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
//        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        long productId = Long.parseLong(req.getParameter("pdId"));
//        int quantity= Integer.parseInt(req.getParameter("quantity"));
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
        OrderDetailDto order =  searchProduct(cart, productId, sizeDto.getName()) ;
        if(session != null && (session.getAttribute(Constants.IS_LOGIN)==null) || session.getAttribute(Constants.IS_LOGIN).equals("false") ) {

            if(order == null)
            {
                OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                    .price(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage())
                    .product(productDto)
                    .size(sizeDto.getName())
                    .quantity(1)
                    .total(1 * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()))
                    .build();
            cart.add(orderDetailDto);

            }
            else
            {
                order.setPrice(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage());
                order.setProduct(productDto);
                order.setSize(sizeDto.getName());
                order.setQuantity(op+ order.getQuantity());
                order.setTotal(order.getQuantity() * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()));
                if(order.getQuantity()==0)
                {
                    cart.remove(order);
                }
            }
//            session.setAttribute("cart", cart);
//            session.setAttribute("cartTotal", calcCartTotal(cart));
//            session.setAttribute("cartSize", calcCartSize(cart));
//            session.setAttribute("dev", Constants.Dev);
        }
        else{
           if(order == null)
           {
               OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                       .price(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage())
                       .product(productDto)
                       .size(sizeDto.getName())
                       .quantity(1)
                       .total(1 * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()))
                       .build();
                        cart.add(orderDetailDto);
           }
           else
           {
                       order.setPrice(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage());
                       order.setProduct(productDto);
                       order.setSize(sizeDto.getName());
                       order.setQuantity(op+ order.getQuantity());
                       order.setTotal(order.getQuantity() * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()));
                       if(order.getQuantity()==0)
                       {
                           cart.remove(order);
                       }
           }



//            session.setAttribute("cart", cart);
//            session.setAttribute("cartTotal", calcCartTotal(cart));
//            session.setAttribute("cartSize", calcCartSize(cart));
//            session.setAttribute("dev", Constants.Dev);
        }
//        doGet(req,response);
        session.setAttribute("cart", cart);
        session.setAttribute("cartTotal", calcCartTotal(cart));
        session.setAttribute("cartSize", calcCartSize(cart));
        session.setAttribute("dev", Constants.Dev);

    }

    private Double calcCartTotal(List<OrderDetailDto> orderDetailDtos)
    {
        double total =Constants.Dev;
        for(OrderDetailDto order : orderDetailDtos)
        {
            total+=order.getTotal();
        }
        return  total;
    }
    private int calcCartSize(List<OrderDetailDto> orderDetailDtos)
    {
        int size =0;
        for(OrderDetailDto order : orderDetailDtos)
        {
            size+=order.getQuantity();
        }
        return  size;
    }

    private OrderDetailDto searchProduct(List<OrderDetailDto> cart, long pdId, String size)
    {
        for(OrderDetailDto orderDetailDto: cart)
        {
            if(orderDetailDto.getProduct().getId() == pdId && orderDetailDto.getSize().equals(size))
            {
                return orderDetailDto;
            }
        }
        return null;
    }
}
