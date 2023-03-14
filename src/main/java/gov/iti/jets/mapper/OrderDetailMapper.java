package gov.iti.jets.mapper;

import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.entity.OrderDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {ProductMapper.class})
public interface OrderDetailMapper {
    OrderDetail toEntity(OrderDetailDto orderDetailDto);

    OrderDetailDto toDto(OrderDetail orderDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetail partialUpdate(OrderDetailDto orderDetailDto, @MappingTarget OrderDetail orderDetail);
}