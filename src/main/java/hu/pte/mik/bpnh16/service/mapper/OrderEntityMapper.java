package hu.pte.mik.bpnh16.service.mapper;

import hu.pte.mik.bpnh16.domain.*;
import hu.pte.mik.bpnh16.service.dto.OrderEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderEntity and its DTO OrderEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {StatusMapper.class})
public interface OrderEntityMapper extends EntityMapper<OrderEntityDTO, OrderEntity> {

    @Mapping(source = "status.id", target = "statusId")
    OrderEntityDTO toDto(OrderEntity orderEntity);

    @Mapping(source = "statusId", target = "status")
    OrderEntity toEntity(OrderEntityDTO orderEntityDTO);

    default OrderEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        return orderEntity;
    }
}
