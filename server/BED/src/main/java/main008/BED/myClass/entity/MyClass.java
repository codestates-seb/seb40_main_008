package main008.BED.myClass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
