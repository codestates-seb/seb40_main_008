package main008.BED.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentDetailId;

    @Column
    private Boolean payed;

    @Column
    private ZonedDateTime payedAt;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;
}
