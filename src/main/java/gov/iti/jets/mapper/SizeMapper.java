package gov.iti.jets.mapper;

import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.entity.Size;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface SizeMapper {
    Size toEntity(SizeDto sizeDto);

    SizeDto toDto(Size size);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Size partialUpdate(SizeDto sizeDto, @MappingTarget Size size);
}