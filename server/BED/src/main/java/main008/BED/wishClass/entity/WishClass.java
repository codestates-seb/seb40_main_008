package main008.BED.wishClass.entity;

import lombok.*;
import main008.BED.contents.entity.Contents;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "wishClass") // 양방향
    private List<Contents> contentsList;

}
