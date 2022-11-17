package main008.BED.carousel.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carousel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carouselId;

    @Column
    private String name;

    @Column
    private String fileKey;

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
