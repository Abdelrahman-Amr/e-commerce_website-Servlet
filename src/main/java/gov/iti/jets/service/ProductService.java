package gov.iti.jets.service;

import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Product;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.persistence.dao.ProductDAO;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService extends BaseService<Product> {
    private volatile static ProductService productService;


    ProductMapper productMapper;

    private ProductDAO productDAO;

    public static ProductService getInstance() {
        if (productService == null) {
            synchronized (ProductService.class) {
                if (productService == null) {
                    productService = new ProductService();
                }
            }
        }
        return productService;
    }

    private ProductService() {
        productDAO = new ProductDAO();
        productMapper = Mappers.getMapper(ProductMapper.class);
    }


    public ProductDto getProductById(Long id) {
//        productDAO = new ProductDAO();
        Product product = productDAO.getProductById(id);
        ProductDto productDto = productMapper.toDto(product);
        productDto.setCatg_id(product.getCatg().getId());
        return productDto;
    }

    public List<ProductDto> listAllProducts(int offset, int maxNoOfRecordsPerPage) {
        List<Product> products = productDAO.listAllProducts(offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }

    public List<ProductDto> listAllProductsByCategory(Long categoryId, int offset, int maxNoOfRecordsPerPage) {
//        productDAO = new ProductDAO();
        List<Product> products = productDAO.listAllProductsByCategory(categoryId, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }
    public List<ProductDto> getProductsByCriteria(Map<String, String> params, int offset, int maxNoOfRecordsPerPage) {
        productDAO = new ProductDAO();
        List<Product> products = productDAO.getProductsByCriteria(params, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());

        return productDtos;
    }

    public List<ProductDto> searchProducts(String searchProduct, int offset, int maxNoOfRecordsPerPage) {
//        productDAO = new ProductDAO();
        List<Product> products = productDAO.searchProducts(searchProduct, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }
    public Long getNoOfReturnedProducts()
    {
//        productDAO = new ProductDAO();
        return productDAO.getNoOfRecords();
    }
    public Product addNewProduct(ProductDto productDto, Boolean active) {

//        productDAO = new ProductDAO();
        Product product = productMapper.toEntity(productDto);
        product.setCreationTime(new Date());
        product.setActive(active);
        if(productDAO.save(product)) {
            System.out.println(product.getId());
            return product;
        }
        return null;
    }

    public void update(Product entity)
    {
        productDAO = new ProductDAO();
        productDAO.update(entity);
    }
    public Product editProduct(ProductDto productDto) {
        Product oldProduct = get(productDto.getId());
        Product product = productMapper.toEntity(productDto);
        product.setActive(oldProduct.getActive());
        product.setCreationTime(oldProduct.getCreationTime());
        update(product);
        return product;
    }

    public Product getFullProductById(Long id) {
        Product product = productDAO.getProductById(id);
        //ProductDto productDto = productMapper.toDto(product);
        return product;
    }
    public Product get(Long id)
    {
        productDAO = new ProductDAO();
        return productDAO.get(id);
    }
}
