package main008.BED.users.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "usersId")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;

    @Column
    private String email;

    @Column
    private String userName;

    @Column
    private String profileImage;

    @Column
    private int totalCoin;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER;
    }

//    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
//    private UserPage userPage;

//    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
//    private MyUploadClass myUploadClass;
//
//    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
//    private MyClass myClass;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Warning> warningList;

//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Bookmark> bookmarkList;

}
