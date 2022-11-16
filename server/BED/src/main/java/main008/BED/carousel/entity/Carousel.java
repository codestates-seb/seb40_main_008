package main008.BED.carousel.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public class Carousel {

    @Value("classpath:carousel/1.png")
    public Resource firstImage;

    @Value("classpath:carousel/2.png")
    public Resource secondImage;

    @Value("classpath:carousel/3.png")
    public Resource thirdImage;

    @Value("classpath:carousel/4.png")
    public Resource forthImage;

    @Value("classpath:carousel/5.png")
    public Resource fifthImage;
}
