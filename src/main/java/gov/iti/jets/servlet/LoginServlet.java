
package gov.iti.jets.servlet;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet{

    CustomerService customerService;

    @Override
    public void init()
    {
        customerService = new CustomerService();
    }

     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//         CustomerDTO dto = CustomerDTO.builder().userName("abdo").email("abdo@abdo.com").build();
//         Customer customer = customerService.get(52);
//         customer.setCreditLimit(2000);
//         customerService.update(customer);
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
            writer.write("1");
        }else{
            writer.write("0");
        }

    }
    
}

