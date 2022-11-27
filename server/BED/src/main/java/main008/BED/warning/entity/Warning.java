package main008.BED.warning.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;
import main008.BED.wish.entity.Wish;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "warningId")
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warningId;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column
    private ZonedDateTime createdAt;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "USERS_ID")
    private Users users;

    @ManyToOne // 양방향, cascade.all, Lazy
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;

    public void addUsers(Users users) {
        if (this.users != null) {
            this.users.getWarningList().remove(this);
        }
        this.users = users;
        users.getWarningList().add(this);
    }

    public void addUploadClass(UploadClass uploadClass) {
        if (this.uploadClass != null) {
            this.uploadClass.getWarningList().remove(this);
        }
        this.uploadClass = uploadClass;
        uploadClass.getWarningList().add(this);
    }
}
