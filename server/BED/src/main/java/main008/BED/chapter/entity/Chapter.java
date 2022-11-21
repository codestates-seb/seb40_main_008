package main008.BED.chapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.uploadClass.entity.UploadClass;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CHAPTERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;

    @Column
    private String chapterOrder;

    @Column
    private String title;

    @Column
    private String thumbnail;

    @Column
    private String fileKey;

    @ManyToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL) // 양방향
    private List<UploadClass> uploadClassList;

    public void discloseContent() {
        if (contents.getChapterList().isEmpty() || uploadClassList.isEmpty()) {
            contents.setDisclosure(false);
        } else {
            contents.setDisclosure(true);
        }
    }

}
