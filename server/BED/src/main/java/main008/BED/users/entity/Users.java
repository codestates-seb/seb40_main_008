package main008.BED.users.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.contents.entity.Contents;
import main008.BED.review.entity.Review;
import main008.BED.users_page.entity.UsersPage;
import main008.BED.warning.entity.Warning;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String profileImage;

    @Column
    private int coin;

    @Column
    private UserRole role;

    @Column
    private ZonedDateTime createdAt;

    @OneToMany(mappedBy = "users")
    private List<Bookmark> bookmarkList;

    @OneToMany(mappedBy = "users")
    private List<Contents> contentsList;

    @OneToMany(mappedBy = "users")
    private List<Review> reviewList;

    @OneToOne(mappedBy = "users")
    private UsersPage usersPage;

    @OneToMany(mappedBy = "users")
    private List<Warning> warningList;

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
