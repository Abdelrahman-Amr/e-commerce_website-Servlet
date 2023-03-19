package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Product;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductDAO extends BaseDAO<Product> {
    private volatile static ProductDAO productDAO;
    private Long noOfRecords;
    private ProductDAO() {
        super(Product.class, MyLocal.getInstance().get());
    }

    public static ProductDAO getInstance() {
        if (productDAO == null) {
            synchronized (ProductDAO.class) {
                if (productDAO == null) {
                    productDAO = new ProductDAO();
                }
            }
        }
        return productDAO;
    }

    public Product getProductById(Long id) {
        TypedQuery query = entityManager.createQuery("select p from Product p where p.id= :id", Product.class);
        query.setParameter("id", id);
        Optional<Product> product = query.getResultStream().findFirst();
        if (product.isPresent())
            return product.get();
        return null;
    }

    public List<Product> listAllProducts(int offset, int noOfRecords) {
        TypedQuery query = entityManager.createQuery("select p from Product p", Product.class).setMaxResults(noOfRecords).setFirstResult(offset);
        List<Product> allProducts = query.getResultList();
        Query query2 = entityManager.createQuery("select count(p) from Product p");
        this.noOfRecords = (Long) query2.getSingleResult();
        return allProducts;
    }

    public List<Product> listAllProductsByCategory(Long categoryId, int offset, int noOfRecords) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.catg.id =:catId", Product.class).setMaxResults(noOfRecords).setFirstResult(offset);
        query.setParameter("catId", categoryId);
        List<Product> result = query.getResultList();
        Query query2 = entityManager.createQuery("select count(p) from Product p where p.catg.id =:catId");
        query2.setParameter("catId", categoryId);
        this.noOfRecords = (Long) query2.getSingleResult();
        return result;
    }
    public List<Product> searchProducts(String searchProduct, int offset, int maxNoOfRecordsPerPage) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.name like : name ", Product.class).setMaxResults(maxNoOfRecordsPerPage).setFirstResult(offset);
        query.setParameter("name", "%" + searchProduct + "%");
        List<Product> result = query.getResultList();
        Query query2 = entityManager.createQuery("select count(p) from Product p where p.name like : name ");
        query2.setParameter("name", "%" + searchProduct + "%");
        Long result2 = (Long) query2.getSingleResult();
        this.noOfRecords = result2;
        return result;
    }

    public Long getNoOfRecords() {
        return noOfRecords;
    }
}
