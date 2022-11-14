package main008.BED.coinCharge.repository;

import main008.BED.coinCharge.entity.CoinCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinChargeRepository extends JpaRepository<CoinCharge, Long> {
}
