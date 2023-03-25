package gov.iti.jets.servlet;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.dto.OrderMasterDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.*;
import gov.iti.jets.service.*;
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
import java.util.Date;
import java.util.List;

public class OrderServlet  extends HttpServlet {

    OrderMasterService orderMasterService;
    OrderDetailService orderDetailService;

    CustomerService customerService;
    OrderMasterMapper orderMasterMapper;
    CustomerMapper customerMapper;
    MyOrderDetailMapperImpl orderDetailMapper;

//    SizeService sizeService;


    @Override
    public void init()
    {
        orderDetailService = OrderDetailService.getInstance();
        customerService = CustomerService.getInstance();
        orderMasterService = OrderMasterService.getInstance();
//        sizeService =  SizeService.getInstance();
        orderMasterMapper = Mappers.getMapper(OrderMasterMapper.class);
        customerMapper = Mappers.getMapper(CustomerMapper.class);
//        orderDetailMapper = Mappers.getMapper(OrderDetailMapper.class);
        orderDetailMapper = new MyOrderDetailMapperImpl();


    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("customer")==null || session.getAttribute("cart") ==null )
        {
            writer.write("0");
            return ;
        }
        CustomerDto customerDto = (CustomerDto)session.getAttribute("customer");
        Customer customer = customerService.get(customerDto.getId());
        List<OrderDetailDto> orderDetailDtos = ( List<OrderDetailDto>)session.getAttribute("cart");

//        Long customerId = Long.parseLong(req.getParameter("custId"));
        Long isConfirm = Long.parseLong(req.getParameter("isConfirm"));
//        session.setAttribute("cart", cart);
//        session.setAttribute("cartTotal", calcCartTotal(cart));
//        session.setAttribute("cartSize", calcCartSize(cart));
        session.setAttribute("dev", Constants.Dev);
        if(isConfirm == 1)
        {
//            customerService.refresh(customer);
            OrderMaster orderMaster = new OrderMaster();
                    orderMaster.setDate(LocalDate.now());
                    orderMaster.setIsDone(false);
                    orderMaster.setTotal((double)session.getAttribute("cartTotal"));
                    orderMaster.setIsCart(false);
                    orderMaster.setShipping(Constants.Dev);
                    orderMaster.setCust(customer);
                    orderMaster.setOrderDetails(orderDetailMapper.toEntities(orderDetailDtos));
                    orderMasterService.save(orderMaster);
                    for(OrderDetail orderDetail :orderMaster.getOrderDetails())
                    {
                        orderDetailService.save(orderDetail);
                    }
        }
        else if(isConfirm == 0){

        }
        else{
            writer.write("0");
            return ;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doGet(req,response);
    }
}
