package main008.BED.warning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column
    private ZonedDateTime createdAt;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "USRES_ID")
    private Users users;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;
}
