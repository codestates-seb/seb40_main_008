package main008.BED.coinCharge.repository;

import main008.BED.coinCharge.entity.CoinCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinChargeRepository extends JpaRepository<CoinCharge, Long> {

    @Query(value =
            "SELECT * FROM coin_charge c " +
            "WHERE c.user_page_id = :user_page_id"
            , nativeQuery = true)
    Optional<CoinCharge> findByUserPage(Long user_page_id);
}
