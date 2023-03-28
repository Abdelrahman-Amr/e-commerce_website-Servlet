package gov.iti.jets.persistence.dao;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import gov.iti.jets.service.CustomerService;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer>{

    public CustomerDAO()
    {
        super(Customer.class, MyLocal.getInstance().get());
    }


    public Customer login(String email , String password)
    {
        Query query=entityManager.createQuery("from Customer c where c.email=:u and c.password =:p");
        query.setParameter("u",email);
        query.setParameter("p",password);
        List<Customer> customers=query.getResultList();
        if(customers.size()==0)
        {
            return null;
        }

        return customers.get(0);

    }

    public boolean isEmailExist(String email) {
        System.out.println(email);
        Query query=entityManager.createQuery("from Customer c where c.email=:u ");
        query.setParameter("u",email);
        List<Customer> customers=query.getResultList();
        System.out.println(customers.size());
        if(customers.size()>0)
        {
            return true;
        }
        return false;
    }


    public List<Customer> findAll() {
        CriteriaQuery<Customer> q1 = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> category = q1.from(Customer.class);
        q1.select(category);
        return  entityManager.createQuery(q1).getResultList();
    }

    public List<Customer> getCustomerList(int index) {
//        Query query=entityManager.createNativeQuery("select * from coffee_point.customer limit "+index+",10;",Customer.class);
                //.createQuery("from Customer c limit :ind,10");
        //query.setParameter("ind",index);
        Query query=entityManager.createQuery(" from Customer c",Customer.class).setFirstResult(index).setMaxResults(10);
        List<Customer> customers=query.getResultList();
//        customers.forEach((e)->System.out.println(e.getUserName()));
        //System.out.println(customers);
        return customers;
    }

    public int getRecordsCount() {
        return entityManager.createQuery("from Customer c").getResultList().size();
    }
}
