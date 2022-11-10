package main008.BED.content.entity;

import lombok.Getter;
import lombok.Setter;
import main008.BED.tag.entity.Tag;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

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

    private String summary;

    private String tutorDetail;

    private String classDetail;

    private Boolean reserve;

    private int price;

    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @OneToMany
    @JoinColumn(name = "TAG_ID")
    private List<Tag> tags;

    public enum Purchase {
        PURCHASED,
        NOT_PURCHASED
    }
}
