package main008.BED.coinCharge.repository;

import main008.BED.coinCharge.entity.CoinChargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinChargeDetailRepository extends JpaRepository<CoinChargeDetail, Long> {
    Optional<CoinChargeDetail> findByTid(String tid);

    @Query(value =
            "SELECT * FROM coin_charge_detail c " +
            "WHERE c.coin_charge_id = :coin_charge_id " +
            "AND c.aid IS NOT NULL"
            , nativeQuery = true)
    Optional<List<CoinChargeDetail>> findByCoinCharge(Long coin_charge_id);

    @Query(value =
            "SELECT * FROM coin_charge_detail c " +
            "WHERE c.coin_charge_id = :coin_charge_id " +
            "AND c.coin_charge_detail_id = :coin_charge_detail_id"
            , nativeQuery = true)
    Optional<CoinChargeDetail> findByCoinChargeAndId(Long coin_charge_id, Long coin_charge_detail_id);

}
