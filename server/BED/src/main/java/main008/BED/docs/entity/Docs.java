package main008.BED.docs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.uploadClass.entity.UploadClass;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column
    private String documents;

    @OneToOne
    @JoinColumn(name = "UPLOAD_CLASS_ID")
    private UploadClass uploadClass;
}
