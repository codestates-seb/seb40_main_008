package main008.BED.userPage.entity;

import lombok.*;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPageId;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToOne(mappedBy = "userPage", cascade = CascadeType.ALL)
    private CoinCharge coinCharge;

    @OneToMany(mappedBy = "userPage", cascade = CascadeType.ALL)
    private List<Contents> contentsList;
}
