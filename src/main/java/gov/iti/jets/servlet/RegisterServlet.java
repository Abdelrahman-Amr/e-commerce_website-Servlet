package gov.iti.jets.servlet;

import java.io.IOException;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDTO;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet{

    CustomerService customerService;

    @Override
    public void init()
    {

        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/registered.html");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String customerJson = new String( req.getInputStream().readAllBytes());
        System.out.println(customerJson);
        RegistrationCustomerDTO customerDTO = new Gson().fromJson(customerJson, RegistrationCustomerDTO.class);
        System.out.println(customerDTO.getUserName());
        if(customerService.signUp(customerDTO)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("isLogin","true");
            session.setAttribute("customer",customerDTO);
            System.out.println("sign up successfully "+customerDTO.getEmail());
            resp.getWriter().write("1");
        } else {
            resp.getWriter().write("0");
        }
    }
}
