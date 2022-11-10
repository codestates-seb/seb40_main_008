//package main008.BED.users.functions.adminUsers;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import main008.BED.users.entity.Users;
//import main008.BED.users.functions.tutorUsers.TutorUsers;
//import main008.BED.warning.entity.Warning;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity(name = "ADMINS_FUNCTIONS")
//@Getter
//@Builder
//@AllArgsConstructor
//public class AdminUsers {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
///*
//    @OneToMany
//    @JoinColumn(name = "USERS_ID")
//    private List<Users> users;
//*/
//
//    @OneToOne
//    @JoinColumn(name = "TUTORS_ID")
//    private TutorUsers tutorUsers;
//
//    @OneToMany
//    @JoinColumn(name = "WARNING_ID")
//    private List<Warning> warnings;
//}
