package main008.BED.carousel.mapper;

import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.entity.Carousel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarouselMapper {

    CarouselDto.ResponseDto entityToResponseDto(Carousel carousel);

    Carousel postDtoToEntity(CarouselDto.Post post);

}
