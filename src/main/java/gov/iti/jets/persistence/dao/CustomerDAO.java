package gov.iti.jets.persistence.dao;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.mapper.CustomerMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Date;
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
            System.out.println("can't login");
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

}
