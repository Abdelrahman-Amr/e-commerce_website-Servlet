package gov.iti.jets.mapper;

import gov.iti.jets.dto.AdminProductDto;
import gov.iti.jets.dto.ProductDto;
import gov.iti.jets.entity.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    Product toEntity(AdminProductDto productDto);

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}