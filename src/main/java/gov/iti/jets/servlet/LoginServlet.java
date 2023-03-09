
package gov.iti.jets.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet{
    
     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
         System.out.println(this);
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
//        RequestDispatcher rd = req.getRequestDispatcher("/views/header.jsp");
        PrintWriter writer = response.getWriter();

        if(email.equalsIgnoreCase("abdo@iti.com") && password.equals("123"))
        {
            writer.write("1");
        }else{
            writer.write("0");

        }
    }
    
}

