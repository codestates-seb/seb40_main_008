package main008.BED.review.entity;

import lombok.*;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int starRate;

    @Column
    private String comments;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne // 양방향
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;


}
