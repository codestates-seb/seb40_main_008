package main008.BED.review.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    /**
     * private User user;
     *
     * private Content content;
     */



    public enum ReviewPoint {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }
}
