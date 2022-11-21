package main008.BED.coinCharge.repository;

import main008.BED.coinCharge.entity.CoinChargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinChargeDetailRepository extends JpaRepository<CoinChargeDetail, Long> {

    List<CoinChargeDetail> findByCoinChargeCoinChargeId(Long coinChargeId);
}
