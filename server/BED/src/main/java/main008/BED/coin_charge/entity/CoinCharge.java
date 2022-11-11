package main008.BED.coin_charge.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private int coinAmount;

    private ZonedDateTime boughtAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;
}
