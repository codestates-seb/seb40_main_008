package main008.BED.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String displayName;

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
