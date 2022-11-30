package Ranking.service;

import Ranking.entity.RankingEntity;
import Ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class RankingService {
    private final RankingRepository rankingRepository;

    public RankingEntity getRanking(String categoriesName){
        RankingEntity ranking = find
    }
}
