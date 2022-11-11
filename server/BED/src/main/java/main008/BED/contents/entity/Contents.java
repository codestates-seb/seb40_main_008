package main008.BED.contents.entity;

import lombok.*;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String image;

    @Column
    private String video;

    @Column
    private String title;

    @Column
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column
    private int wish;

    @Column
    private int price;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @Column
    private int like;

    public enum Payment {
        PAYMENT("구매"),
        NOT_PAYMENT("비구매");

        @Getter
        private String status;

        Payment(String status) {
            this.status = status;
        }
    }
}
