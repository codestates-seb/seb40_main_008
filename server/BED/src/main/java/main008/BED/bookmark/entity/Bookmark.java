package main008.BED.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String memo;

    private ZonedDateTime savedAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    /*
    @OneToMany
    @JoinColumn(name = "CONTENTS_ID")
    private List<Contents> contents;
    */
}