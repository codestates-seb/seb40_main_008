package main008.BED.coin_charge.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coin_charge.repository.CoinChargeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CoinChargeService {

    private final CoinChargeRepository coinChargeRepository;

}
