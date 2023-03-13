package gov.iti.jets.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = req.getSession(false);
        PrintWriter writer = response.getWriter();

        if(session!=null && session.getAttribute("isLogin").equals("true") )
      {
          session.invalidate();
          writer.write("1");
      }else{
            writer.write("0");
        }

//        RequestDispatcher rd = req.getRequestDispatcher("home");
//       rd.forward(req,response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
      doGet(req,response);
    }
}
