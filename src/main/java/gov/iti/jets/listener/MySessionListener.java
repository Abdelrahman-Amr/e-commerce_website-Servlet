package gov.iti.jets.listener;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

public class MySessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        OrderDetailService orderDetailService = OrderDetailService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        OrderMasterService orderMasterService = OrderMasterService.getInstance();
        MyOrderDetailMapperImpl orderDetailMapper = new MyOrderDetailMapperImpl();

        HttpSession session = hse.getSession();
        CustomerDto customerDto = (CustomerDto)session.getAttribute("customer");
        Customer customer = customerService.get(customerDto.getId());
        List<OrderDetailDto> cart = (List<OrderDetailDto>) session.getAttribute("cart");

            orderMasterService.removeCart(customer.getId());
            if(cart!=null && cart.size()>0) {
                OrderMaster orderMaster = new OrderMaster();
                orderMaster.setDate(LocalDate.now());
                orderMaster.setIsDone(false);
                orderMaster.setTotal((double) session.getAttribute("cartTotal"));
                orderMaster.setIsCart(true);
                orderMaster.setShipping(Constants.Dev);
                orderMaster.setCust(customer);
//              orderMaster.setOrderDetails(orderDetailMapper.toEntities(cart));
                orderMasterService.save(orderMaster);
                for (OrderDetail orderDetail : orderDetailMapper.toEntities(cart)) {
                    orderDetail.setInvo(orderMaster);
                    orderDetailService.save(orderDetail);
                }
            }
    }

}
