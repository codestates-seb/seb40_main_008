package Ranking.repository;

import main008.BED.contents.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class RankingRepository extends JpaRepository<categoriesName, Integer> {
     CategoriesName findByCategoriesName(String categoriesName);
}
