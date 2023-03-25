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


    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        orderDetailMapper = new MyOrderDetailMapperImpl();

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
        int isCredit = Integer.parseInt(req.getParameter("isCredit"));



            orderMasterService.deleteCart(customer.getId());
            OrderMaster orderMaster = new OrderMaster();
                    orderMaster.setDate(LocalDate.now());
                    orderMaster.setIsDone(false);
                    orderMaster.setTotal((double)session.getAttribute("cartTotal"));
                    orderMaster.setIsCart(false);
                    orderMaster.setShipping(Constants.Dev);
                    orderMaster.setCust(customer);
//                    orderMaster.setOrderDetails(orderDetailMapper.toEntities(orderDetailDtos));
                    orderMasterService.save(orderMaster);
                    for(OrderDetail orderDetail :orderDetailMapper.toEntities(orderDetailDtos))
                    {
                            orderDetail.setInvo(orderMaster);
                            orderDetailService.save(orderDetail);
                    }
                    if(isCredit ==1)
                    {
                        if(orderMaster.getTotal()>customer.getCreditLimit())
                        {
                            writer.write("0");
                            return;
                        }
                        customer.setCreditLimit(customer.getCreditLimit() - orderMaster.getTotal());
                        customerService.refresh(customer);
                        customerDto.setCreditLimit(customer.getCreditLimit());
                        session.setAttribute("customer",customerDto);
                    }
            session.removeAttribute("cart");
            session.removeAttribute("cartTotal");
            session.removeAttribute("cartSize");
            session.removeAttribute("dev");

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        doGet(req,response);
    }
}
