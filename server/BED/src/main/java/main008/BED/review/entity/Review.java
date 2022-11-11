package main008.BED.review.entity;


import lombok.Getter;
import lombok.Setter;
import main008.BED.content.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String comments;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "CONTENT_ID")
    private Contents content;



    public enum ReviewPoint {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }
}
