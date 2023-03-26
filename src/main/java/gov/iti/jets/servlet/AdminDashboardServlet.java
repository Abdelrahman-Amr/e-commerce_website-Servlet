package gov.iti.jets.servlet;

import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.OrderDetailService;
import gov.iti.jets.service.ProductService;
import org.mapstruct.factory.Mappers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AdminDashboardServlet extends HttpServlet {
    private ProductService productService;
    private OrderDetailService orderDetailService;
    private CustomerService customerService;
//    private ProductMapper productMapper;

    @Override
    public void init() {
        productService = ProductService.getInstance();
        orderDetailService = OrderDetailService.getInstance();
        customerService = CustomerService.getInstance();
//        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long totalProducts = productService.getNoOfReturnedProducts(new HashMap<String, String>());
        request.getServletContext().setAttribute("totalProducts", totalProducts);

        Long totalOrders = orderDetailService.getOrdersCount();
        request.getServletContext().setAttribute("totalOrders", totalOrders);

        int totalCustomers = customerService.getRecordsCount();
        request.getServletContext().setAttribute("totalCustomers", totalCustomers);

        Double totalRevenue = productService.getTotalRevenue();
        request.getServletContext().setAttribute("totalRevenue", totalRevenue);


        RequestDispatcher rd;
        response.setContentType("text/html");
        rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);


        rd = request.getRequestDispatcher("/views/dashboard.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
