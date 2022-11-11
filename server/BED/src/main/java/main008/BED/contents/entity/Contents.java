package main008.BED.contents.entity;

import lombok.*;
import main008.BED.users.entity.Users;

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
    private String image;

    @Column
    private String video;

    @Column
    private String title;

    @Column
    private String summary;

    @Column
    private String tutorDetail;

    @Column
    private String classDetail;

    @Column
    private Boolean reserve;

    @Column
    private int price;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

/*    @OneToMany
    @JoinColumn(name = "TAGS_ID")
    private List<Tags> tags;*/

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
