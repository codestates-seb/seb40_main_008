package main008.BED.users_page.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.coin_charge.entity.CoinCharge;
import main008.BED.my_class.entity.MyClass;
import main008.BED.upload_class.entity.UploadClass;
import main008.BED.users.entity.Users;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @OneToOne
    @JoinColumn(name = "COIN_CHARGE_ID")
    private CoinCharge coinCharge;

    @OneToOne
    @JoinColumn(name = "MY_CLASS_ID")
    private MyClass myClass;

    @OneToOne
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;
}
