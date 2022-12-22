package main008.BED.mainPage.entity;

import lombok.*;
import main008.BED.contents.entity.Contents;
import main008.BED.myClass.entity.MyClass;
import main008.BED.userPage.entity.UserPage;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "mainPage") // 양방향
    private List<Contents> contentsList;

    @OneToOne
    @JoinColumn(name = "USER_PAGE_ID")
    private UserPage userPage;

    @OneToOne
    @JoinColumn(name = "MY_CLASS_ID")
    private MyClass myClass;

}
