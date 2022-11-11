package main008.BED.coin_charge.repository;

import main008.BED.coin_charge.entity.CoinCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinChargeRepository extends JpaRepository<CoinCharge, Long> {
}
