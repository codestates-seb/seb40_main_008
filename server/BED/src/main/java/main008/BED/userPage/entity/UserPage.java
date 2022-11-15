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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPageId;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToOne
    @JoinColumn(name = "COIN_CHARGE_ID")
    private CoinCharge coinCharge;

    @OneToMany(mappedBy = "userPage")
    private List<Contents> contentsList;
}
