package gov.iti.jets.service;

import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.mapper.OrderDetailMapper;
import gov.iti.jets.mapper.OrderMasterMapper;
import gov.iti.jets.persistence.dao.OrderDetailDAO;
import gov.iti.jets.persistence.dao.OrderMasterDAO;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    public List<OrderDetail> getOrderDetailList(OrderMaster orderMaster, int index) {
        orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getOrderDetailList(orderMaster, index);
    }

    public int getOrderDetailCountForOrder(Long orderMasterId) {
        orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getOrderDetailCountForOrder(orderMasterId);
    }
    public Long getOrdersCount() {
        orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getTotalOrderCount();
    }

}
