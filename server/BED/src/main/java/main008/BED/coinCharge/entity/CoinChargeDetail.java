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
    private Long coinChargeDetailId; // 결제번호

    @Column
    private String itemName; // 상품명

    @Column
    private int quantity; // 상품 수량

    @Column
    private int tax; // 부가세

    @Column(unique = true)
    private String tid; // 결제 번호

    @Column
    private String aid; // 요청 고유 번호

    @Column
    private int chargeAmount; // 결제 금액

    @Column
    private ZonedDateTime approvedAt;

    @ManyToOne
    @JoinColumn(name = "COIN_CHARGE_ID")
    private CoinCharge coinCharge;
}
