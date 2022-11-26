package main008.BED.likes.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "likesId")

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

    @OneToMany(mappedBy = "likes", cascade = CascadeType.ALL)
    private List<LikesDetail> likesDetails;
}
