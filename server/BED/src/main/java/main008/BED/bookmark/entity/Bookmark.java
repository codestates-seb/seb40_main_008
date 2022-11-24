package main008.BED.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime modifiedAt;

//    @Column
//    private String timeline;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "USERS_ID")
    private Users users;
}
