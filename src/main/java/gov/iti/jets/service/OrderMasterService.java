package gov.iti.jets.service;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.mapper.SizeMapper;
import gov.iti.jets.persistence.dao.CustomerDAO;
import gov.iti.jets.persistence.dao.OrderMasterDAO;
import gov.iti.jets.persistence.dao.SizeDAO;
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
}
