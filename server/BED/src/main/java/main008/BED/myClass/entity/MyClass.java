package main008.BED.myClass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import main008.BED.takingClass.entity.TakingClass;
import main008.BED.wishClass.entity.WishClass;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "WISH_CLASS_ID")
    private WishClass wishClass;

    @OneToOne
    @JoinColumn(name = "TAKING_CLASS_ID")
    private TakingClass takingClass;

}
