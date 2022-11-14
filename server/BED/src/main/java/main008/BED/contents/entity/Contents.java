package main008.BED.contents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String classProfile;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column
    private Boolean payment;

    @Column
    private int likes;

    @Column
    private int wish;

    @Column
    private Categories categories;

    public enum Categories {
        DIGITAL_DRAWING("디지털 드로잉"),
        MUSIC("음악"),
        DRAWING("드로잉"),
        CRAFTS("공예"),
        LIFE_STYLE("라이프 스타일"),
        COOKING_BEVERAGE("요리/음료"),
        PHOTO_MEDIA("사진/영상"),
        BAKING_DESSERT("베이킹/디저트"),
        FINANCE("금융/재테크"),
        SUCCESS_MIND("성공 마인드"),
        FOUNDED_SIDELINE("창업/부업"),
        PROGRAMMING("프로그래밍"),
        PRODUCTIVITY("생산성"),
        DATA_SCIENCE("데이터 사이언스"),
        MARKETING("마켓팅"),
        PRODUCT_PLANNING("제품 기획"),
        BUSINESS("비즈니스"),
        VIDEO_3D("영상/3D"),
        ENGLISH("영어"),
        SECOND_LANGUAGE("제2외국어"),
        LANGUAGE_EXAM("외국어 시험"),
        CHILD_EDUCATION("아이 교육"),
        PARENTS_EDUCATION("부모 교육");

        @Getter
        private final String keyword;

        Categories(String keyword) {
            this.keyword = keyword;
        }
    }
}
