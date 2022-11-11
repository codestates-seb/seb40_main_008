package main008.BED.class_index.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.upload_class.entity.UploadClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String index;

    @Column
    private String title;

//    @Column
//    private String subTitle;

    @OneToMany(mappedBy = "classIndex")
    private List<UploadClass> uploadClass;

    @ManyToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;
}