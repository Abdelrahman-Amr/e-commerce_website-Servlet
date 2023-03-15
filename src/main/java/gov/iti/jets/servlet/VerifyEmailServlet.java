package gov.iti.jets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gov.iti.jets.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import gov.iti.jets.presistance.connection.Service;
import jakarta.persistence.TypedQuery;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //System.out.println("emaillllllllllll   "+req.getParameter("email"));
        if(customerService.checkEmail(req.getParameter("email"))) {
            out.write("1");
        } else {
            out.write("0");
        }
    }
}
