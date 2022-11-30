package main008.BED.wish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.entity.Contents;
import main008.BED.myClass.entity.MyClass;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @Column
    private Boolean wished;

    @ManyToOne
    @JoinColumn(name = "CONTENTS_ID")
    private Contents contents;

    // contents < wish
    // myclass < wish
    @ManyToOne
    @JoinColumn(name = "MY_CLASS_ID")
    private MyClass myClass;

    public void addContents(Contents contents) {
        this.contents = contents;
        if (!this.contents.getWishes().contains(this)) {
            this.contents.getWishes().add(this);
        }
    }

    public void addMyClass(MyClass myClass) {
        this.myClass = myClass;
        if (!this.myClass.getWishes().contains(this)) {
            this.myClass.addWish(this);
        }
    }
}
