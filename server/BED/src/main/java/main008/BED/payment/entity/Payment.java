package main008.BED.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    /*
    강좌 개설 시 가격 인풋
    */
    @Column
    private Integer price;

    // 컨텐츠 < payment
    @OneToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<PaymentDetail> paymentDetails;
}
