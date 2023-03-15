package gov.iti.jets.persistence.dao;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Customer;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Queue;

public class CategoryDAO extends BaseDAO<Category>{
    public CategoryDAO()
    {
        super(Category.class, DBFactory.getInstance().createEntityManager());
    }


    public List<Category> getAll()
    {
        Query query = entityManager.createQuery("from Category");
        List<Category> categories = query.getResultList();
        return  categories;
    }

}
