package main008.BED.review.entity;


import lombok.*;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int star_rate;

    @Column
    private String comment;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime modifiedAt;


    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "CONTENT_ID")
    private Contents contents;


}
