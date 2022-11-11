package main008.BED.class_index.entity;

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
public class ClassIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String index;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String video;

    @ManyToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;
}
