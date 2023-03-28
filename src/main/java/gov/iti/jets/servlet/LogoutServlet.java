package gov.iti.jets.servlet;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.mapper.MyOrderDetailMapperImpl;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.service.OrderDetailService;
import gov.iti.jets.service.OrderMasterService;
import gov.iti.jets.util.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class LogoutServlet extends HttpServlet {

    OrderMasterService orderMasterService;
    OrderDetailService orderDetailService;

    CustomerService customerService;
    OrderMasterMapper orderMasterMapper;
    CustomerMapper customerMapper;
    MyOrderDetailMapperImpl orderDetailMapper;

    @Override
    public void init() {
        orderDetailService = OrderDetailService.getInstance();
        customerService = CustomerService.getInstance();
        orderMasterService = OrderMasterService.getInstance();
        orderMasterMapper = Mappers.getMapper(OrderMasterMapper.class);
        customerMapper = Mappers.getMapper(CustomerMapper.class);

    }
        @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        orderDetailMapper = new MyOrderDetailMapperImpl();
      HttpSession session = req.getSession(false);
        PrintWriter writer = response.getWriter();
        CustomerDto customerDto = (CustomerDto)session.getAttribute("customer");
        Customer customer = customerService.get(customerDto.getId());
        List<OrderDetailDto> cart = (List<OrderDetailDto>) session.getAttribute("cart");

        if(session!=null && session.getAttribute("isLogin").equals("true") )
      {
          orderMasterService.removeCart(customer.getId());
          if(cart!=null && cart.size()>0)
          {
//              OrderMaster order = orderMasterService.searchForCart(customer.getId());
              OrderMaster orderMaster = new OrderMaster();
              orderMaster.setDate(LocalDate.now());
              orderMaster.setIsDone(false);
              orderMaster.setTotal((double)session.getAttribute("cartTotal"));
              orderMaster.setIsCart(true);
              orderMaster.setShipping(Constants.Dev);
              orderMaster.setCust(customer);
//              orderMaster.setOrderDetails(orderDetailMapper.toEntities(cart));
              orderMasterService.save(orderMaster);
              for(OrderDetail orderDetail :orderDetailMapper.toEntities(cart))
              {
                  orderDetail.setInvo(orderMaster);
                  orderDetailService.save(orderDetail);
              }
          }
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
