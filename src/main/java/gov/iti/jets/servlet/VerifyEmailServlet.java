package gov.iti.jets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import gov.iti.jets.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import gov.iti.jets.presistance.connection.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //System.out.println("emaillllllllllll   "+req.getParameter("email"));
        if(veryEmail(req.getParameter("email"))) {
            out.write("valid");
        } else {
            out.write("invalid");
        }
    }

    private boolean veryEmail(String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("accountINFO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        User c = entityManager.find(User.class, 2);
        System.out.println(email);
        if(c!=null) return true;
        return false;
    }
    
}
