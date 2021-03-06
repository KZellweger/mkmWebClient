package ch.skaldenmagic.cardmarket.autopricing.domain.mapper;

import ch.skaldenmagic.cardmarket.autopricing.domain.entity.ArticlePriceEntity;
import ch.skaldenmagic.cardmarket.autopricing.domain.mapper.dtos.ArticlePriceDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Kevin Zellweger
 * @Date 01.11.20
 */
@Mapper(componentModel = "spring")
public interface ArticlePriceMapper {

  @AfterMapping
  default void calculatePriceDifference(ArticlePriceEntity order,
      @MappingTarget ArticlePriceDto dto) {
    dto.setPriceDifference(order.getPrice().subtract(order.getRecommendedPrice()));
  }

  ArticlePriceDto toDto(ArticlePriceEntity entity);


}
