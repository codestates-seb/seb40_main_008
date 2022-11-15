package main008.BED.users.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
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
}
