package gov.iti.jets.mapper;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.SizeDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.Size;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface SizeMapper {
    Size toEntity(SizeDto sizeDto);

    SizeDto toDto(Size size);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Size partialUpdate(SizeDto sizeDto, @MappingTarget Size size);

    default ArrayList<SizeDto> toDTOs(Collection<Size> customers) {
        return customers.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<SizeDto>::new));
    }

    default ArrayList<Size> toEntities(Collection<SizeDto> CustomerDtos) {
        return CustomerDtos.stream().map(dto -> toEntity(dto)).collect(toCollection(ArrayList<Size>::new));
    }
}
