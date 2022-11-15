package main008.BED.docs.entity;

import lombok.*;
import main008.BED.uploadClass.entity.UploadClass;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Lob
    private byte[] data; // file

    @Column
    private String name; // file name +++ 추가

//    강의 업로드 클래스로 이동
//    @OneToOne
//    @JoinColumn(name = "UPLOAD_CLASS_ID")
//    private UploadClass uploadClass;
}
