package main008.BED.contents.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.chapter.entity.Chapter;
import main008.BED.likes.entity.Likes;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.payment.entity.Payment;
import main008.BED.userPage.entity.UserPage;
import main008.BED.users.entity.Users;
import main008.BED.wish.entity.Wish;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "contenssId")
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentsId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String fileKey; // thumbnail key for delete it in s3

    @Column(nullable = false)
    private int likesCount;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String details;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Categories categories;

    @Column(nullable = false)
    private Boolean disclosure = false; // 콘텐츠 공개 여부

    @Column(columnDefinition = "TEXT", nullable = false)
    private String tutorDetail;

    @Column(nullable = false)
    public int countLecture = 0;
//    public static int countLecture = 0; // 콘텐츠 공개 여부 결정 도움을 위한 필드

    public enum Categories implements EnumModel{
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

        private final String value;


        Categories(String value) {
            this.value = value;
        }


        // 역직렬화
        @JsonCreator
        public static Categories from(String value) {
            for (Categories categories : Categories.values()) {
                if (categories.getValue().equals(value)) {
                    return categories;
                }
            }
            return null;
        }

        // 추상 메서드 구현
        @Override
        public String getKey() {
            return name();
        }

        // 직렬화
        @JsonValue
        public String getValue() {
            return value;
        }
    }

    @OneToOne(mappedBy = "contents", cascade = CascadeType.ALL) // 양방향
    private Likes likes;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne // 양방향
    @JoinColumn(name = "MAIN_PAGE_ID")
    private MainPage mainPage;

    @ManyToOne // 양방향
    @JoinColumn(name = "MY_UPLOAD_CLASS_ID")
    private MyUploadClass myUploadClass;

    @ManyToOne // 양방향
    @JoinColumn(name = "USER_PAGE_ID")
    private UserPage userPage;

    @ManyToOne
    @JoinColumn(name = "MY_CLASS_ID")
    private MyClass myClass;

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<Wish> wishes;

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<Chapter> chapterList;


    @OneToOne(mappedBy = "contents", cascade = CascadeType.ALL)
    private Payment payment;


    // 양방향 매핑 시 편의메서드 필수
    public void addWish(Wish wish) {
        this.wishes.add(wish);
        if (wish.getContents() != this) {
            wish.addContents(this);
        }
    }

    public void addLikes(Likes likes) {
        this.likes = likes;
        if (likes.getContents() != this) {
            likes.addContents(this);
        }
    }

    public void disclosureDecision() {
        if (this.countLecture == 0) {
            this.disclosure = false;
        } else {
            this.disclosure = true;
        }
    }

}
