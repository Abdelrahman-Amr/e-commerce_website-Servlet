package gov.iti.jets.mapper;

import gov.iti.jets.dto.OrderMasterDto;
import gov.iti.jets.entity.OrderMaster;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface OrderMasterMapper {
    OrderMaster toEntity(OrderMasterDto orderMasterDto);

    OrderMasterDto toDto(OrderMaster orderMaster);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderMaster partialUpdate(OrderMasterDto orderMasterDto, @MappingTarget OrderMaster orderMaster);
}