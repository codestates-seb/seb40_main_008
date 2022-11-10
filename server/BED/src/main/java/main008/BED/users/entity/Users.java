package main008.BED.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String profileImg;

    @Column
    private int coin;

    @Column
    private UserRole role;

    @Column
    private ZonedDateTime createdAt;

    public enum UserRole {
        ROLE_ADMIN("관리자"),
        ROLE_USER("일반 회원"),
        ROLE_TUTOR("강사 회원");

        @Getter
        private String status;

        UserRole(String status) {
            this.status = status;
        }
    }
}
