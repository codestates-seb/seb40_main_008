package main008.BED.users.entity;

import lombok.*;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.entity.PaymentDetail;
import main008.BED.userPage.entity.UserPage;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

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
    private int totalCoin;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Role role;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER;
    }

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private UserPage userPage;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private MyUploadClass myUploadClass;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private MyClass myClass;
}
