package gov.iti.jets.service;

import gov.iti.jets.dto.AdminProductDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Product;
import gov.iti.jets.mapper.CategoryMapper;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.persistence.dao.CategoryDAO;
import gov.iti.jets.persistence.dao.ProductDAO;
import org.mapstruct.factory.Mappers;

import javax.xml.crypto.Data;
import java.util.*;
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

    public ProductService() {
        productDAO = ProductDAO.getInstance();
        dao = productDAO;
        productMapper = Mappers.getMapper(ProductMapper.class);
    }


    public ProductDto getProductById(Long id) {
        Product product = productDAO.getProductById(id);
        ProductDto productDto = productMapper.toDto(product);
        return productDto;
    }
    public List<ProductDto> listAllProducts() {
        List<Product> products = productDAO.listAllProducts();
        List<ProductDto> productDtos =
                products.stream()
                .map(product -> productMapper.toDto(product))
                .collect(Collectors.toList());
        return productDtos;
    }
    public List<ProductDto> listAllProductsByCategory(Long categoryId) {
        List<Product> products = productDAO.listAllProductsByCategory(categoryId);
        List<ProductDto> productDtos =
                products.stream()
                .map(product -> productMapper.toDto(product))
                .collect(Collectors.toList());
        return productDtos;
    }

    public Product addNewProduct(ProductDto productDto, Boolean active) {

        Product product = productMapper.toEntity(productDto);
        product.setCreationTime(new Date());
        product.setActive(active);
        if(productDAO.save(product)) {
            System.out.println(product.getId());
            return product;
        }
        return null;
    }
}
