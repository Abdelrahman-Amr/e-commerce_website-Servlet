package gov.iti.jets.mapper;

import gov.iti.jets.dto.CustomerDto;
import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.entity.Customer;
import gov.iti.jets.entity.OrderDetail;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {ProductMapper.class})
public interface OrderDetailMapper {
    OrderDetail toEntity(OrderDetailDto orderDetailDto);

    OrderDetailDto toDto(OrderDetail orderDetail);

    default ArrayList<OrderDetailDto> toDTOs(Collection<OrderDetail> customers) {
        return customers.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<OrderDetailDto>::new));
    }

    default Set<OrderDetail> toEntities(Collection<OrderDetailDto> orderDetailDtos) {
        return orderDetailDtos.stream().map(dto -> toEntity(dto)).collect(toCollection(HashSet<OrderDetail>::new));
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetail partialUpdate(OrderDetailDto orderDetailDto, @MappingTarget OrderDetail orderDetail);
}
