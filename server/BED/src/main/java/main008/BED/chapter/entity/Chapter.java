package main008.BED.chapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String thumbnail;

    @Column
    private String title;

    @Column
    private String chapterOrder;

    @ManyToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;
}
