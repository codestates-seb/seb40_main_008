package main008.BED.docs.entity;

import lombok.*;
import main008.BED.uploadClass.entity.UploadClass;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docsId;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Lob
    private byte[] data; // file


    @Column
    private String name; // file name +++ 추가


}
