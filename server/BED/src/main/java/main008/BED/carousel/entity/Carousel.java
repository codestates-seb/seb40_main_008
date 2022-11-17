package main008.BED.carousel.entity;


import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carousel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carouselId;

    @Column
    private String name;

    @Column
    private String keys;

    @Column
    private String imageUrl;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String redirectUrl;

/*  <static resources>
    @Value("classpath:carousel/1.png")
    public Resource firstImage;

    @Value("classpath:carousel/2.png")
    public Resource secondImage;

    @Value("classpath:carousel/3.png")
    public Resource thirdImage;

    @Value("classpath:carousel/4.png")
    public Resource forthImage;

    @Value("classpath:carousel/5.png")
    public Resource fifthImage;*/
}
