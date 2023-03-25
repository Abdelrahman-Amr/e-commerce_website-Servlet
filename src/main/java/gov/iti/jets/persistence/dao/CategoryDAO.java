package gov.iti.jets.persistence.dao;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderMaster;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Queue;

public class CategoryDAO extends BaseDAO<Category>{
    private volatile static CategoryDAO categoryDAO;

    private CategoryDAO()
    {
        super(Category.class, MyLocal.getInstance().get());
    }

    public static CategoryDAO getInstance() {
        if (categoryDAO == null) {
            synchronized (CategoryDAO.class) {
                if (categoryDAO == null) {
                    categoryDAO = new CategoryDAO();
                }
            }
        }
        return categoryDAO;
    }

    public List<Category> getAll()
    {
        Query query = entityManager.createQuery("from Category");
        List<Category> categories = query.getResultList();
        return  categories;
    }

    public Category getCategoryByName(String categoryName) {
        Query query = entityManager.createQuery("from Category where name := ?");
        query.setParameter(1,categoryName);
        Category category = (Category) query.getSingleResult();
        if(category!=null) {
            System.out.println(category.getName());
            return category;
        }
        return null;
    }

}
