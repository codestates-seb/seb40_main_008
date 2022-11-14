package main008.BED.likes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;
}
