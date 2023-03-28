package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Customer;
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

    public List<OrderDetail> getOrderDetailList(OrderMaster orderMaster, int index) {

        Query query=entityManager.createQuery(" from OrderDetail o where invo =?1",OrderDetail.class)
                .setParameter(1,orderMaster).setFirstResult(index).setMaxResults(10);
        List<OrderDetail> orders=query.getResultList();
        System.out.println(orders);
        return orders;
    }

    public int getOrderDetailCountForOrder(Long orderMasterId) {
        Query query=entityManager.createQuery(" from OrderDetail o where invo =?1",OrderDetail.class)
                .setParameter(1,orderMasterId);
        List<OrderDetail> orders=query.getResultList();

        return orders.size();
    }

}
