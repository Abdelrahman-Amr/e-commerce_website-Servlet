package gov.iti.jets.mapper;

import gov.iti.jets.dto.AdminProductDto;
import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Category;
import gov.iti.jets.entity.Product;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    Product toEntity(AdminProductDto productDto);

    ProductDto toDto(Product product);

    default ArrayList<ProductDto> toDTOs(Collection<Product> products) {
        return products.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<ProductDto>::new));
    }

    default ArrayList<Product> toEntities(Collection<ProductDto> productDtos) {
        return productDtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Product>::new));
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}
