package main008.BED.coin_charge.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.coin_charge.mapper.CoinChargeMapper;
import main008.BED.contents.service.ContentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinChargeController {

    private final ContentService contentService;
    private final CoinChargeMapper coinChargeMapper;
}
