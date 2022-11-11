package main008.BED.upload_class.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main008.BED.class_index.entity.ClassIndex;
import main008.BED.contents.entity.Contents;
import main008.BED.users_page.entity.UsersPage;

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

    @Column
    private String title;

    @Column
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column
    private String video;

    @Column
    private String thumbnail;

    @Column(columnDefinition = "TEXT")
    private String classBase;

    @ManyToOne
    @JoinColumn(name = "CLASS_INDEX_ID")
    private ClassIndex classIndex;

    @OneToOne(mappedBy = "uploadClass")
    private UsersPage usersPage;

}
