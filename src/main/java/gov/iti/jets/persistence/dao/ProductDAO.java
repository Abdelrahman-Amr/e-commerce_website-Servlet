package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.Product;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDAO extends BaseDAO<Product> {
    private volatile static ProductDAO productDAO;

    private  ProductDAO() {
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
        TypedQuery query = entityManager.createQuery("select p from Product p where p.id=id", Product.class);
        Product product = (Product) query.getSingleResult();
        return product;
    }
    public List<Product> listAllProducts() {
        TypedQuery query = entityManager.createQuery("select p from Product p", Product.class);
        List<Product> allProducts = query.getResultList();
        return allProducts;
    }

}
