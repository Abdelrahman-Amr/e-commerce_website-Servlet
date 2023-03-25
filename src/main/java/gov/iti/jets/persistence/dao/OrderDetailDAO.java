package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;

import java.util.List;

public class OrderDetailDAO extends BaseDAO<OrderDetail> {
    private volatile static OrderDetailDAO orderDetailDAO;

    private OrderDetailDAO() {
        super(OrderDetail.class, MyLocal.getInstance().get());
    }

    public static OrderDetailDAO getInstance() {
        if (orderDetailDAO == null) {
            synchronized (OrderDetailDAO.class) {
                if (orderDetailDAO == null) {
                    orderDetailDAO = new OrderDetailDAO();
                }
            }
        }
        return orderDetailDAO;
    }


    public List<OrderDetail> getAll() {
        Query query = entityManager.createQuery("from OrderDetail");
        List<OrderDetail> orders = query.getResultList();
        return orders;
    }

}
