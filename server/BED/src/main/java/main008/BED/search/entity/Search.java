package main008.BED.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.content.entity.Content;
import main008.BED.tag.entity.Tag;
//import main008.BED.users.functions.tutorUsers.TutorUsers;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "CONTENTS_ID")
    private List<Content> contents;

//    @OneToMany
//    @JoinColumn(name = "TUTORS_ID")
//    private List<TutorUsers> tutorUsers;

    @OneToMany
    @JoinColumn(name = "TAG_ID")
    private List<Tag> tags;
}
