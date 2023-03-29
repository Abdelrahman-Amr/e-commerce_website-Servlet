
package gov.iti.jets.servlet;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.mapper.MyOrderDetailMapperImpl;
import gov.iti.jets.mapper.OrderDetailMapper;
import gov.iti.jets.persistence.dao.DBFactory;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.OrderDetailService;
import gov.iti.jets.service.OrderMasterService;
import gov.iti.jets.util.Constants;
import gov.iti.jets.util.MyLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class LoginServlet extends HttpServlet{

    CustomerService customerService;
    OrderMasterService orderMasterService;
    OrderDetailService orderDetailService;
    MyOrderDetailMapperImpl orderDetailMapper;

    @Override
    public void init()
    {
        customerService = CustomerService.getInstance();
        orderMasterService = OrderMasterService.getInstance();
        orderDetailService = OrderDetailService.getInstance();
    }

     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        rd.include(req, response);
        
        rd = req.getRequestDispatcher("/views/login.html");
        rd.include(req, response);

        rd = req.getRequestDispatcher("/views/footer.jsp");
        rd.include(req, response);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        orderDetailMapper =new MyOrderDetailMapperImpl();

         response.setContentType("application/json");
         String email = req.getParameter("email");
         String password = req.getParameter("password");
         CustomerDto customerDto = customerService.login(email,password);
         PrintWriter writer = response.getWriter();

        if(customerDto!=null)
        {
            HttpSession session = req.getSession(true);
            session.setAttribute("isLogin","true");
            session.setAttribute("customer",customerDto);
            OrderMaster cart = orderMasterService.searchForCart(customerDto.getId());
            if(cart!=null)
            {
                List<OrderDetailDto> orderDetailDtoList = orderDetailMapper.toDTOs(cart.getOrderDetails());
                session.setAttribute("cart", orderDetailMapper.toDTOs(cart.getOrderDetails()));
                session.setAttribute("cartTotal",orderMasterService.calcCartTotal(orderDetailDtoList));
                session.setAttribute("cartSize", orderMasterService.calcCartSize(orderDetailDtoList));
                session.setAttribute("dev", Constants.Dev);
            }
            if(customerDto.getIsAdmin()) {
                session.setAttribute("isAdmin","true");
                System.out.println("admin");
                writer.write("2");
            } else {
                session.setAttribute("isAdmin","false");
                System.out.println("user");
                writer.write("1");
            }
        }else{
            writer.write("0");
        }

    }
    
}

