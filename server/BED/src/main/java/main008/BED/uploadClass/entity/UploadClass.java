package main008.BED.uploadClass.entity;

import lombok.*;
import main008.BED.chapter.entity.Chapter;
import main008.BED.docs.entity.Docs;
import main008.BED.review.entity.Review;
import main008.BED.warning.entity.Warning;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uploadClassId;

    @Column
    private String title;

    @Column
    private String video;

    @Column
    private String name; // video name - eg: "video.mp4"

    @Column
    private String fileKey; // video fileKey - for deleting video in s3

    @ManyToOne // 양방향
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;

    @OneToOne
    @JoinColumn(name = "DOCS_ID")
    private Docs docs;

    @OneToMany(mappedBy = "uploadClass", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "uploadClass", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Warning> warningList;
}
