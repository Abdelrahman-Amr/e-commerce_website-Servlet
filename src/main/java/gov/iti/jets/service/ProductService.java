package gov.iti.jets.service;

import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Product;
import gov.iti.jets.mapper.ProductMapper;
import gov.iti.jets.persistence.dao.ProductDAO;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService extends BaseService<Product> {
    private volatile static ProductService productService;


    ProductMapper productMapper;

    private ProductDAO productDAO;

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

    public List<ProductDto> listAllProducts(int offset, int maxNoOfRecordsPerPage) {
        List<Product> products = productDAO.listAllProducts(offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }

    public List<ProductDto> listAllProductsByCategory(Long categoryId, int offset, int maxNoOfRecordsPerPage) {
        List<Product> products = productDAO.listAllProductsByCategory(categoryId, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }
    public List<ProductDto> getProductsByCriteria(Map<String, String> params, int offset, int maxNoOfRecordsPerPage) {
        List<Product> products = productDAO.getProductsByCriteria(params, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }

    public List<ProductDto> searchProducts(String searchProduct, int offset, int maxNoOfRecordsPerPage) {
        List<Product> products = productDAO.searchProducts(searchProduct, offset, maxNoOfRecordsPerPage);
        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> productMapper.toDto(product))
                        .collect(Collectors.toList());
        return productDtos;
    }
    public Long getNoOfReturnedProducts() {
        return ProductDAO.getInstance().getNoOfRecords();
    }
}
