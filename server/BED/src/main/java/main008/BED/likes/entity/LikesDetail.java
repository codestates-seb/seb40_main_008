package main008.BED.likes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikesDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesDetailsId;

    @Column
    private Boolean liked;

    @Column
    private ZonedDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "LIKES_ID")
    private Likes likes;
}
