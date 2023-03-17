package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Product;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProductDAO extends BaseDAO<Product> {
    private volatile static ProductDAO productDAO;

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

    public List<Product> listAllProducts() {
        TypedQuery query = entityManager.createQuery("select p from Product p", Product.class);
        List<Product> allProducts = query.getResultList();
        return allProducts;
    }

    public List<Product> listAllProductsByCategory(Long categoryId) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.catg.id =:categoryId", Product.class);
        query.setParameter("categoryId", categoryId);
        List<Product> categoryProductList = query.getResultList();
        return categoryProductList;
    }

}
