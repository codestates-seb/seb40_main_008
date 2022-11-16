package main008.BED.myUploadClass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.userPage.entity.UserPage;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUploadClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myUploadClassId;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToOne
    @JoinColumn(name = "USER_PAGE_ID")
    private UserPage userPage;

    @OneToMany(mappedBy = "myUploadClass") // 양방향
    private List<Contents> contentsList;
}
