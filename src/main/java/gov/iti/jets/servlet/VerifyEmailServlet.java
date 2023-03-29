package gov.iti.jets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import gov.iti.jets.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyEmailServlet extends HttpServlet {

    CustomerService customerService;

    @Override
    public void init()
    {

        customerService = CustomerService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        if(customerService.checkEmail(req.getParameter("email"))) {
            out.write("1");
        } else {
            out.write("0");
        }
    }
}
