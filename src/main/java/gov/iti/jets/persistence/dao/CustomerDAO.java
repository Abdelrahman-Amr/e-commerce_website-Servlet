package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CustomerDAO extends BaseDAO<Customer>{

    public CustomerDAO()
    {
        super(Customer.class, DBFactory.getInstance().createEntityManager());

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



}
