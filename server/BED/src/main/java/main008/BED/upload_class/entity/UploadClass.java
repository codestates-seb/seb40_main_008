package main008.BED.upload_class.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.contents.entity.Contents;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String video;

    private String thumbnail;

    private String classBase;

    @OneToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;
}
