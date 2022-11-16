package main008.BED.carousel.controller;

import main008.BED.carousel.dto.CarouselDto;
import main008.BED.carousel.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("carousel")
    public ResponseEntity<ByteArrayResource> getCarousel() throws IOException, InterruptedException {
        List<byte[]> imageBytes = carouselService.readCarousel();
        List<ByteArrayResource> imageList = imageBytes.stream()
                .map(bytes -> new ByteArrayResource(bytes))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageList.get(1));
    }

}
