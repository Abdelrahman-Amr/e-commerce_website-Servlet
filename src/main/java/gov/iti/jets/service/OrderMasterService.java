package gov.iti.jets.service;

import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.mapper.SizeMapper;
import gov.iti.jets.persistence.dao.OrderMasterDAO;
import gov.iti.jets.persistence.dao.SizeDAO;
import org.mapstruct.factory.Mappers;

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
}
