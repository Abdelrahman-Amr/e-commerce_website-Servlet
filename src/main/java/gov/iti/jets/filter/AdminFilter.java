package gov.iti.jets.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession httpSession = httpRequest.getSession(false);
            RequestDispatcher rd;

            if (httpSession != null) {
                if (httpSession.getAttribute("isLogin") != null && httpSession.getAttribute("isLogin").equals("true")) {
                    if (httpSession.getAttribute("isLogin") != null && httpSession.getAttribute("isAdmin").equals("true")) {
                        chain.doFilter(request, response);
                    } else {
                        rd = httpRequest.getRequestDispatcher("home");
                        rd.forward(request, response);
                    }
                } else {
                    rd = httpRequest.getRequestDispatcher("login");
                    rd.forward(request, response);
                }
            } else {
                rd = httpRequest.getRequestDispatcher("login");
                rd.forward(request, response);
            }
        }
    }
}
