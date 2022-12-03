package main008.BED.carousel.mapper;

import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.entity.Carousel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarouselMapper {

    List<CarouselDto.ResponseDto> entityToResponseDto(List<Carousel> carousel);

    Carousel postDtoToEntity(CarouselDto.Post post);

    default CarouselDto.ListResponseDto responseToListDto(List<CarouselDto.ResponseDto> carousels) {

        return new CarouselDto.ListResponseDto(carousels);
    }

}
