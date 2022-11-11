package main008.BED.my_class.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.users_page.entity.UsersPage;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "myClass")
    private List<Contents> contentsList;

    @OneToOne(mappedBy = "myClass")
    private UsersPage usersPage;
}
