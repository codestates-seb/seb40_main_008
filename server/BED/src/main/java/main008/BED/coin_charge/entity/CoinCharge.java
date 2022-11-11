package main008.BED.coin_charge.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.users.entity.Users;
import main008.BED.users_page.entity.UsersPage;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int coinAmount;

    @Column
    private ZonedDateTime boughtAt;

    @OneToOne(mappedBy = "coinCharge")
    private UsersPage usersPage;
}
