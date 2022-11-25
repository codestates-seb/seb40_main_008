package main008.BED.likes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesId;

    @Column
    private int likesCount = 0;

//    @OneToOne
//    @JoinColumn(name = "USERS_ID")
//    private Users users;

    @OneToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;

    /**
     * DB와 영속성 컨텍스트의 값이 다를 경우 생기는
     * 응답 버그 해결 위해 repository에 (clearAutomatically = true) 를 추가하였더니
     * failed to lazily initialize a collection of role: ~ could not initialize proxy - no Session 에러 발생
     * 위 에러는 양방향 관계에서 조회 시 영속성 컨텍스트 연결이 끊기는 오류
     * 위 에러 해결을 위해 fetch = FetchType.EAGER 추가함
     */
    @OneToMany(mappedBy = "likes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LikesDetail> likesDetails;

    public void addContents(Contents contents) {
        this.contents = contents;
        if (contents.getLikes() != this) {
            contents.addLikes(this);
        }
    }
}
