package Ranking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name = "Rank")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners((AuditingEntityListener.class))
public class RankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategoriesId;

    @Column(nullable = false)
    private String categoriesName;

    @Column
    private int views;
}
