package gov.iti.jets.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.Gson;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.RegistrationCustomerDTO;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.MyOrderDetailMapperImpl;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.OrderDetailService;
import gov.iti.jets.service.OrderMasterService;
import gov.iti.jets.util.Constants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet{


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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("/views/header.jsp");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/registered.html");
        rd.include(request, response);

        rd = request.getRequestDispatcher("/views/footer.jsp");
        rd.include(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerJson = new String( req.getInputStream().readAllBytes());
        RegistrationCustomerDTO customerDTO = new Gson().fromJson(customerJson, RegistrationCustomerDTO.class);

        if(customerService.signUp(customerDTO)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("isLogin","true");
            session.setAttribute("customer",customerDTO);
            OrderMaster cart = orderMasterService.searchForCart(customerDTO.getId());
            if(cart!=null)
            {
                session.setAttribute("cart", orderDetailMapper.toDTOs(cart.getOrderDetails()));
                session.setAttribute("cartTotal", cart.getTotal());
                session.setAttribute("cartSize", cart.getOrderDetails().size());
                session.setAttribute("dev", Constants.Dev);
            }
            resp.getWriter().write("1");
        } else {
            resp.getWriter().write("0");
        }
    }
}
