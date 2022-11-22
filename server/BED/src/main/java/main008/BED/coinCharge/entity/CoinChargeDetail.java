package main008.BED.coinCharge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinChargeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coinChargeDetailId;

    @Column
    private int chargeAmount;

    @Column
    private ZonedDateTime boughtAt;

    @ManyToOne
    @JoinColumn(name = "COIN_CHARGE_ID")
    private CoinCharge coinCharge;
}
