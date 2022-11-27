package main008.BED.chapter.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;

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

    public void addContents(Contents contents) {
        if (this.contents != null) {
            this.contents.getChapterList().remove(this);
        }
        this.contents = contents;
        contents.getChapterList().add(this);
    }

    public void discloseContent() {
        if (contents.getChapterList().isEmpty() || uploadClassList.isEmpty()) {
            contents.setDisclosure(false);
        } else {
            contents.setDisclosure(true);
        }
    }



}
