package gov.iti.jets.persistence.dao;

import gov.iti.jets.entity.*;
import gov.iti.jets.entity.Product;
import gov.iti.jets.util.MyLocal;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductDAO extends BaseDAO<Product> {
    public Long noOfRecords;


    public ProductDAO() {
        super(Product.class, MyLocal.getInstance().get());
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

    public List<Product> getProductsByCriteria(Map<String, String> params, int offset, int maxNoOfRecordsPerPage) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(params, criteriaBuilder, productRoot);
        noOfRecords = getReturnedProductsCount(params);

        criteriaQuery.select(productRoot).where(predicate);
        //execute query
        if (params.get("price") != null) {
            if (params.get("price").equals("asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get("price")));
            }
            else if (params.get("price").equals("desc")) {
                  criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get("price")));
            }
        }
        List<Product> resultList = entityManager.createQuery(criteriaQuery).setMaxResults(maxNoOfRecordsPerPage).setFirstResult(offset).getResultList();
        return resultList;
    }

    private static Predicate getPredicate(Map<String, String> params, CriteriaBuilder criteriaBuilder, Root<Product> productRoot) {
        Predicate predicate = criteriaBuilder.conjunction();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String param = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                switch (param) {
                    case "name":
                        //add predicate
                        break;
                    case "catId":
                        Predicate newPredicate = criteriaBuilder.and(criteriaBuilder.equal(productRoot.get(Product_.catg).get(Category_.id), Long.valueOf(params.get("catId"))));
                        predicate = criteriaBuilder.and(predicate, newPredicate);
                        break;
                    case "Search":
                        value = value.trim();
                        newPredicate = criteriaBuilder.and(criteriaBuilder.like(productRoot.get("name"), "%" + value + "%"));
                        predicate = criteriaBuilder.and(predicate, newPredicate);
                        break;
                }
            }
        }
        return predicate;
    }

    public Long getReturnedProductsCount(Map<String, String> params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(params, criteriaBuilder, productRoot);

        criteriaQuery.select(criteriaBuilder
                        .count(productRoot))
                .where(predicate);
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
//        System.out.println(typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString());
        return typedQuery.getResultList().get(0);
    }

    public List<Product> getPriorityProducts()
    {
        Query query=entityManager.createQuery(" from Product p where p.priority=1 and p.active=true",Product.class).setMaxResults(6);
        List<Product> products=query.getResultList();
        return  products;
    }

//    public List<Product> getMostSellingProducts() {
//        Query query = entityManager.createQuery(" select o.product from OrderDetail o group by o.product  order by o.quantity desc", Product.class).setMaxResults(6);
//        List<Product> products = query.getResultList();
//        System.out.println("products = " + products.size());
//        return products;}

    public List<Product> getMostSellingProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<OrderDetail> orderDetail = cq.from(OrderDetail.class);

        cq.select(orderDetail.get("product"))
                .groupBy(orderDetail.get("product"))
                .orderBy(cb.desc(cb.sum(orderDetail.get("quantity"))));

        List<Product> products = entityManager.createQuery(cq)
                .setFirstResult(0)
                .setMaxResults(6)
                .getResultList();
        return products;
    }

    public List<Product> getOffersProducts()
    {
        Query query=entityManager.createQuery(" from Product p where p.discount>0 and p.active=true order by p.discount desc",Product.class).setMaxResults(3);
        List<Product> products=query.getResultList();
        return  products;
    }

    public List<Product> getRelatedProducts(long catId)
    {
        Query query=entityManager.createQuery(" from Product p where p.catg.id  = :catId ",Product.class).setMaxResults(4);
        query.setParameter("catId",catId);
        List<Product> products=query.getResultList();
        return  products;
    }

    public Double getTotalRevenue() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<OrderDetail> orderDetailRoot = cq.from(OrderDetail.class);
        cq.select(cb.sum(orderDetailRoot.get("total")));
        return entityManager.createQuery(cq).getSingleResult();
    }
    public void deleteProduct(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Product> delete = cb.createCriteriaDelete(Product.class);
        Root<Product> root = delete.from(Product.class);
        delete.where(cb.equal(root.get("id"), id));
        entityManager.getTransaction().begin();
        entityManager.createQuery(delete).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void deleteProduct2(Long id) {

        Query query=entityManager.createQuery(" from Product p where p.id  = :id ",Product.class);
        query.setParameter("id",id);
        List<Product> products=query.getResultList();
        if(products!=null && products.size()>0)
        {
            Product product = products.get(0);
            entityManager.getTransaction().begin();
            product.setActive(false);
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        }

    }
}
