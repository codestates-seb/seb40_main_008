package main008.BED.warning.entity;

import lombok.Getter;
import lombok.Setter;
import main008.BED.content.entity.Contents;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warningId;

    private String reason;

    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users user;

    @OneToOne
    @JoinColumn(name = "CONTENT_ID")
    private Contents content;

}
