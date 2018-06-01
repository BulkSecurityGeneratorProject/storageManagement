package hu.pte.mik.bpnh16.service.mapper;

import hu.pte.mik.bpnh16.domain.*;
import hu.pte.mik.bpnh16.service.dto.StockTakingItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockTakingItem and its DTO StockTakingItemDTO.
 */
@Mapper(componentModel = "spring", uses = {StockTakingMapper.class, ProductMapper.class, StatusMapper.class})
public interface StockTakingItemMapper extends EntityMapper<StockTakingItemDTO, StockTakingItem> {

    @Mapping(source = "stockTaking.id", target = "stockTakingId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "status.id", target = "statusId")
    StockTakingItemDTO toDto(StockTakingItem stockTakingItem);

    @Mapping(source = "stockTakingId", target = "stockTaking")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "statusId", target = "status")
    StockTakingItem toEntity(StockTakingItemDTO stockTakingItemDTO);

    default StockTakingItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockTakingItem stockTakingItem = new StockTakingItem();
        stockTakingItem.setId(id);
        return stockTakingItem;
    }
}
