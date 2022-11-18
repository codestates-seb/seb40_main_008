package main008.BED.users.entity;

import lombok.*;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.payment.entity.Payment;
import main008.BED.userPage.entity.UserPage;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private int coin;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER;
    }

    @OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
    private UserPage userPage;

    @OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
    private MyUploadClass myUploadClass;

    @OneToOne(mappedBy = "users", cascade = CascadeType.REMOVE)
    private MyClass myClass;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Payment payment;
}
