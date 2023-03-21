package gov.iti.jets.servlet;

import gov.iti.jets.dto.*;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.service.CategoryService;
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
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(req.getSession(false));
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
        long productId = Long.parseLong(req.getParameter("pdId"));
        int quantity= Integer.parseInt(req.getParameter("quantity"));
        long sizeId= Integer.parseInt(req.getParameter("sizeId"));
        SizeDto sizeDto = sizeService.getSizeById(sizeId);
        ProductDto productDto = productService.getProductById(productId);
        HttpSession session =  req.getSession(false);
        if(session!=null && session.getAttribute("cart")==null)
        {
            session.setAttribute("cart", new ArrayList<>());
        }
        List<OrderDetailDto> cart = (List<OrderDetailDto>) session.getAttribute("cart");
        OrderDetailDto order =  searchProduct(cart, productId) ;
        if(session != null && (session.getAttribute(Constants.IS_LOGIN)==null) || session.getAttribute(Constants.IS_LOGIN).equals("false") ) {

            if(order == null)
            {
                OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                    .price(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage())
                    .product(productDto)
                    .size(sizeDto.getName())
                    .quantity(quantity)
                    .total(quantity * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()))
                    .build();
            cart.add(orderDetailDto);
            session.setAttribute("cart", cart);
            session.setAttribute("cartTotal", calcCartTotal(cart));
            session.setAttribute("cartSize", calcCartSize(cart));
            session.setAttribute("dev", Constants.Dev);
            }
            else
            {
                order.setPrice(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage());
                order.setProduct(productDto);
                order.setSize(sizeDto.getName());
                order.setQuantity(quantity+ order.getQuantity());
                order.setTotal(order.getQuantity() * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()));
            }

        }
        else{
           if(order == null)
           {
               OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                       .price(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage())
                       .product(productDto)
                       .size(sizeDto.getName())
                       .quantity(quantity)
                       .total(quantity * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()))
                       .build();
                        cart.add(orderDetailDto);
           }
           else
           {
                       order.setPrice(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage());
                       order.setProduct(productDto);
                       order.setSize(sizeDto.getName());
                       order.setQuantity(quantity+ order.getQuantity());
                       order.setTotal(order.getQuantity() * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()));
           }



            session.setAttribute("cart", cart);
            session.setAttribute("cartTotal", calcCartTotal(cart));
            session.setAttribute("cartSize", calcCartSize(cart));
            session.setAttribute("dev", Constants.Dev);
        }
//        doGet(req,response);
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

    private OrderDetailDto searchProduct(List<OrderDetailDto> cart, long pdId)
    {
        for(OrderDetailDto orderDetailDto: cart)
        {
            if(orderDetailDto.getProduct().getId() == pdId)
            {
                return orderDetailDto;
            }
        }
        return null;
    }
}
