package main008.BED.content.entity;

import lombok.Getter;
import lombok.Setter;
import main008.BED.search.entity.Search;
import main008.BED.tag.entity.Tags;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "TAGS_ID")
    private List<Tags> tags;

/*

    @ManyToOne
    @JoinColumn(name = "SEARCH_ID")
    private Search search;
*/


    public enum Purchase {
        PURCHASED,
        NOT_PURCHASED
    }
}
