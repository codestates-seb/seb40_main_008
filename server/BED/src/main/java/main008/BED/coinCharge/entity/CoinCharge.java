package main008.BED.coinCharge.entity;


import lombok.*;
import main008.BED.userPage.entity.UserPage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coinChargeId;

    @Column
    private ChargeAmount chargeAmount;

    @OneToMany(mappedBy = "coinCharge")
    private List<CoinChargeDetail> coinChargeDetails = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USER_PAGE_ID")
    private UserPage userPage;

    public enum ChargeAmount {
        FIVE(5000),
        TEN(10000),
        TWENTY(20000),
        FIFTY(50000);

        @Getter
        private final int amount;

        ChargeAmount(int amount) {
            this.amount = amount;
        }

    }

}
