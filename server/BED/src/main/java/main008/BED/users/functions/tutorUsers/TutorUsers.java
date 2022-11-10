//package main008.BED.users.functions.tutorUsers;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import main008.BED.users.entity.Users;
//
//import javax.persistence.*;
//
//@Entity(name = "TUTORS_FUNCTIONS")
//@Getter
//@Builder
//@AllArgsConstructor
//public class TutorUsers {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(columnDefinition = "TEXT")
//    private String profile;
//
//    @Column(columnDefinition = "TEXT")
//    private String classDetail;
//
//    @OneToOne
//    @JoinColumn(name = "USERS_ID")
//    private Users users;
//}
