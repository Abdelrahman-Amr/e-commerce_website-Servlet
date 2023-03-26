package gov.iti.jets.service;

import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.OrderDetailMapper;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.persistence.dao.OrderDetailDAO;
import gov.iti.jets.persistence.dao.OrderMasterDAO;
import org.mapstruct.factory.Mappers;

public class OrderDetailService extends BaseService<OrderDetail>{
        private volatile static OrderDetailService orderDetailService;


        OrderDetailMapper orderDetailMapper;

        private OrderDetailDAO orderDetailDAO;

    private OrderDetailService() {
        orderDetailMapper = Mappers.getMapper(OrderDetailMapper.class);
        }
        public static OrderDetailService getInstance() {
            if (orderDetailService == null) {
                synchronized (OrderDetailService.class) {
                    if (orderDetailService == null) {
                        orderDetailService = new OrderDetailService();
                    }
                }
            }
            return orderDetailService;
        }
    public void save(OrderDetail entity)
    {
        orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.save(entity);
    }

    //For Admin Dashboard
    public Long getOrdersCount() {
        orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getTotalOrderCount();
    }

}
