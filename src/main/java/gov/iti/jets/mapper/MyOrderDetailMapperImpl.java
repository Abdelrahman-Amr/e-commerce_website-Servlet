package gov.iti.jets.mapper;

import gov.iti.jets.dto.OrderDetailDto;
import gov.iti.jets.entity.OrderDetail;
import gov.iti.jets.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public class MyOrderDetailMapperImpl {
    private ProductMapper productMapper;

    public MyOrderDetailMapperImpl()
    {
        productMapper = Mappers.getMapper(ProductMapper.class);

    }
     public ArrayList<OrderDetailDto> toDTOs(Collection<OrderDetail> customers) {
        return customers.stream().map(entity -> toDto(entity)).collect(toCollection(ArrayList<OrderDetailDto>::new));
    }

    public  Set<OrderDetail> toEntities(Collection<OrderDetailDto> orderDetailDtos) {
        return orderDetailDtos.stream().map(dto -> toEntity(dto)).collect(toCollection(HashSet<OrderDetail>::new));
    }
    public OrderDetail toEntity(OrderDetailDto orderDetailDto) {
        if ( orderDetailDto == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId( orderDetailDto.getId() );
        orderDetail.setPrice( orderDetailDto.getPrice() );
        orderDetail.setQuantity( orderDetailDto.getQuantity() );
        orderDetail.setSize( orderDetailDto.getSize() );
        orderDetail.setTotal( orderDetailDto.getTotal() );
        orderDetail.setProduct( productMapper.toEntity( orderDetailDto.getProduct() ) );

        return orderDetail;
    }

    public OrderDetailDto toDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDto.OrderDetailDtoBuilder orderDetailDto = OrderDetailDto.builder();

        orderDetailDto.price( orderDetail.getPrice() );
        orderDetailDto.quantity( orderDetail.getQuantity() );
        orderDetailDto.size( orderDetail.getSize() );
        orderDetailDto.total( orderDetail.getTotal() );
        orderDetailDto.product( productMapper.toDto( orderDetail.getProduct() ) );

        return orderDetailDto.build();
    }

    public OrderDetail partialUpdate(OrderDetailDto orderDetailDto, OrderDetail orderDetail) {
        if ( orderDetailDto == null ) {
            return orderDetail;
        }

        if ( orderDetailDto.getId() != null ) {
            orderDetail.setId( orderDetailDto.getId() );
        }
        if ( orderDetailDto.getPrice() != null ) {
            orderDetail.setPrice( orderDetailDto.getPrice() );
        }
        if ( orderDetailDto.getQuantity() != null ) {
            orderDetail.setQuantity( orderDetailDto.getQuantity() );
        }
        if ( orderDetailDto.getSize() != null ) {
            orderDetail.setSize( orderDetailDto.getSize() );
        }
        if ( orderDetailDto.getTotal() != null ) {
            orderDetail.setTotal( orderDetailDto.getTotal() );
        }
        if ( orderDetailDto.getProduct() != null ) {
            if ( orderDetail.getProduct() == null ) {
                orderDetail.setProduct( new Product() );
            }
            productMapper.partialUpdate( orderDetailDto.getProduct(), orderDetail.getProduct() );
        }

        return orderDetail;
    }
}


