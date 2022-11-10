package main008.BED.content.entity;

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
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    private String image;

    private String video;

    private String title;

    private String detail;

    private Boolean reserve;

    private int price;

    private ZonedDateTime createdAt;

    /**
     *     private User user;
     *
     *     private List<Tag> tags;
     */


    public enum Purchase {
        PURCHASED,
        NOT_PURCHASED
    }
}
