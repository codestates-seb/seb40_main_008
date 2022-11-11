package main008.BED.warning.entity;

import lombok.*;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warningId;

    @Column
    private String reason;

    @Column
    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @OneToOne
    @JoinColumn(name = "CONTENT_ID")
    private Contents content;

}
