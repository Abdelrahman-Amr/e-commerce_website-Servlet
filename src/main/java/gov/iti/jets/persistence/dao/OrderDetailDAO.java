package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;

import java.util.List;

public class OrderDetailDAO extends BaseDAO<OrderDetail> {

    public OrderDetailDAO() {
        super(OrderDetail.class, DBFactory.getInstance().createEntityManager());
    }



    public List<OrderDetail> getAll() {
        Query query = entityManager.createQuery("from OrderDetail");
        List<OrderDetail> orders = query.getResultList();
        return orders;
    }

}
