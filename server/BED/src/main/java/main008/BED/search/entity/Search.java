//package main008.BED.search.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import main008.BED.content.entity.Contents;
//import main008.BED.tag.entity.Tags;
////import main008.BED.users.functions.tutorUsers.TutorUsers;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//public class Search {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(mappedBy = "search")
//    private List<Contents> contents;
//
////    @OneToMany
////    @JoinColumn(name = "TUTORS_ID")
////    private List<TutorUsers> tutorUsers;
//
//    @OneToMany
//    @JoinColumn(name = "TAGS_ID")
//    private List<Tags> tags;
//}
