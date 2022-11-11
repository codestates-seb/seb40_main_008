package main008.BED.contents.entity;

import lombok.*;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.class_index.entity.ClassIndex;
import main008.BED.my_class.entity.MyClass;
import main008.BED.review.entity.Review;
import main008.BED.users.entity.Users;
import main008.BED.warning.entity.Warning;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int likes;

    @Column
    private int wish;

    @Column
    private int price;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Category category;

    @Column
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "MY_CLASS_ID")
    private MyClass myClass;

    @OneToMany(mappedBy = "contents")
    private List<Bookmark> bookmarkList;

    @OneToMany(mappedBy = "contents")
    private List<ClassIndex> classIndexList;

    @OneToMany(mappedBy = "contents")
    private List<Review> reviewList;

    @OneToOne(mappedBy = "contents")
    private Warning warning;

    public enum Payment {
        PAYMENT("구매"),
        NOT_PAYMENT("비구매");

        @Getter
        private String status;

        Payment(String status) {
            this.status = status;
        }
    }

    public enum Category {
        MUSIC("음악"),
        SPORTS("스포츠"),
        COOKING("요리"),
        PROGRAMMING("프로그래밍");

        @Getter
        private String status;

        Category(String status) {
            this.status = status;
        }
    }


}
