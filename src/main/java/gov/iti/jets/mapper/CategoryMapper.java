package gov.iti.jets.mapper;

import gov.iti.jets.dto.CategoryDto;
import gov.iti.jets.entity.Category;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);

    default ArrayList<CategoryDto> toDTOs(Collection<Category> categories) {
        return categories.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<CategoryDto>::new));
    }

    default ArrayList<Category> toEntities(Collection<CategoryDto> categoryDTOS) {
        return categoryDTOS.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Category>::new));
    }
}
