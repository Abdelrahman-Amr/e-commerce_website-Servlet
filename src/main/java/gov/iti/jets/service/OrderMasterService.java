package gov.iti.jets.service;

import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.mapper.SizeMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.persistence.dao.OrderMasterDAO;
import gov.iti.jets.persistence.dao.SizeDAO;
import gov.iti.jets.util.Constants;
import jakarta.persistence.Query;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class OrderMasterService extends BaseService<OrderMaster>{
    private volatile static OrderMasterService orderMasterService;


    OrderMasterMapper orderMasterMapper;

    private OrderMasterDAO orderMasterDAO;

    private OrderMasterService() {
        orderMasterMapper = Mappers.getMapper(OrderMasterMapper.class);
    }
    public static OrderMasterService getInstance() {
        if (orderMasterService == null) {
            synchronized (OrderMasterService.class) {
                if (orderMasterService == null) {
                    orderMasterService = new OrderMasterService();
                }
            }
        }
        return orderMasterService;
    }
    public void save(OrderMaster entity)
    {
        orderMasterDAO = new OrderMasterDAO();
        orderMasterDAO.save(entity);
    }

    public OrderMaster searchForCart(Long customerId)
    {
        orderMasterDAO = new OrderMasterDAO();
        return  orderMasterDAO.searchForCart(customerId);
    }

    public void deleteCart(Long customerId)
    {
        orderMasterDAO = new OrderMasterDAO();
        orderMasterDAO.deleteCart(customerId);
    }

    public List<OrderMaster> getOrderList(int index) {
        orderMasterDAO =  new OrderMasterDAO();
        List<OrderMaster> orderList = orderMasterDAO.getOrders((index-1)*10);
        return orderList;
    }

    public int getRecordsCount() {
        orderMasterDAO =  new OrderMasterDAO();
        return orderMasterDAO.getRecordsCount();
    }

    public OrderMaster getOrderMasterById(Long id) {
        return orderMasterDAO.get(id);
    }

    public void removeCart(long customerId)
    {
        orderMasterDAO = new OrderMasterDAO();
        orderMasterDAO.removeCart(customerId);
    }



    public Double calcCartTotal(List<OrderDetailDto> orderDetailDtos)
    {
        double total = Constants.Dev;
        for(OrderDetailDto order : orderDetailDtos)
        {
            total+=order.getTotal();
        }
        return  total;
    }
    public int calcCartSize(List<OrderDetailDto> orderDetailDtos)
    {
        int size =0;
        for(OrderDetailDto order : orderDetailDtos)
        {
            size+=order.getQuantity();
        }
        return  size;
    }

    public OrderDetailDto searchProduct(List<OrderDetailDto> cart, long pdId, String size)
    {
        for(OrderDetailDto orderDetailDto: cart)
        {
            if(orderDetailDto.getProduct().getId() == pdId && orderDetailDto.getSize().equals(size))
            {
                return orderDetailDto;
            }
        }
        return null;
    }

    public void createOrderDetail(ProductDto productDto, SizeDto sizeDto, List<OrderDetailDto> cart)
    {

        OrderDetailDto orderDetailDto = OrderDetailDto.builder()
                .price(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage())
                .product(productDto)
                .size(sizeDto.getName())
                .quantity(1)
                .total(1 * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()))
                .build();
        cart.add(orderDetailDto);
    }

    public void updateOrderDetail(ProductDto productDto, SizeDto sizeDto, OrderDetailDto order, int op)
    {

        order.setPrice(productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage());
        order.setProduct(productDto);
        order.setSize(sizeDto.getName());
        order.setQuantity(op+ order.getQuantity());
        order.setTotal(order.getQuantity() * (productDto.getPrice() + productDto.getPrice() * sizeDto.getPercentage()));
    }
}
