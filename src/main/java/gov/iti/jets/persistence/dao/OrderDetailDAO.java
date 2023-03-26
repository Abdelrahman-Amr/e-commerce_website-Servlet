package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

    public Long getTotalOrderCount() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<OrderDetail> orderDetailRoot = cq.from(OrderDetail.class);
        cq.select(cb.count(orderDetailRoot));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
