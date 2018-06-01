package hu.pte.mik.bpnh16.service.mapper;

import hu.pte.mik.bpnh16.domain.*;
import hu.pte.mik.bpnh16.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductCategoryMapper.class, PriceCategoryMapper.class, StatusMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "productCategory.id", target = "productCategoryId")
    @Mapping(source = "priceCategory.id", target = "priceCategoryId")
    @Mapping(source = "status.id", target = "statusId")
    ProductDTO toDto(Product product);

    @Mapping(source = "productCategoryId", target = "productCategory")
    @Mapping(source = "priceCategoryId", target = "priceCategory")
    @Mapping(source = "statusId", target = "status")
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
