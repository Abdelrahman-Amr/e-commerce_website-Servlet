package gov.iti.jets.servlet;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileServlet extends HttpServlet {

    CustomerService customerService;
    @Override
    public void init()
    {
        customerService =  CustomerService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/profile.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto oldCustomerDto = (CustomerDto) req.getSession(false).getAttribute("customer");
        String customerJson = new String( req.getInputStream().readAllBytes());
        CustomerDto customerDTO = new Gson().fromJson(customerJson, RegistrationCustomerDTO.class);

        customerDTO.setEmail(oldCustomerDto.getEmail());
        customerDTO.setId(oldCustomerDto.getId());
        if(customerService.updateProfile(customerDTO)) {
            HttpSession session = req.getSession(false);
            session.setAttribute("customer",customerDTO);

            resp.getWriter().write("1");
        } else {
            resp.getWriter().write("0");
        }
    }
}
