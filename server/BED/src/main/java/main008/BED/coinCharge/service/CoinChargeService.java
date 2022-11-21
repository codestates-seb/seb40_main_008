package main008.BED.coinCharge.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.coinCharge.entity.CoinChargeDetail;
import main008.BED.coinCharge.repository.CoinChargeDetailRepository;
import main008.BED.coinCharge.repository.CoinChargeRepository;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.repository.UserPageRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoinChargeService {

    private final CoinChargeDetailRepository coinChargeDetailRepository;
    private final CoinChargeRepository coinChargeRepository;
    private final UserPageRepository userPageRepository;
    private final UsersRepository usersRepository;

    public CoinCharge charging(Long usersId, CoinCharge coinChargePost) {

        Users users = usersRepository.findByUsersId(usersId);
        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);
        CoinCharge coinCharge = coinChargeRepository.findByUserPageUserPageId(userPage.getUserPageId());
        List<CoinChargeDetail> coinChargeDetails = coinChargeDetailRepository.findByCoinChargeCoinChargeId(coinCharge.getCoinChargeId());

        CoinChargeDetail coinChargeDetail = new CoinChargeDetail();
        coinChargeDetail.setCoinCharge(coinCharge);
        coinChargeDetail.setChargeAmount(coinChargePost.getChargeAmount().getAmount());
        coinChargeDetail.setBoughtAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        coinChargeDetail = coinChargeDetailRepository.save(coinChargeDetail);

        coinChargeDetails.add(coinChargeDetail);
        coinCharge.setCoinChargeDetails(coinChargeDetails);
        CoinCharge coinCharge1 = coinChargeRepository.save(coinCharge);

        users.setTotalCoin(users.getTotalCoin() + coinChargeDetail.getChargeAmount());
        usersRepository.save(users);

        return coinCharge1;
    }

    public List<CoinChargeDetail> getCoinChargeDetail(Long usersId) {

        UserPage userPage = userPageRepository.findByUsersUsersId(usersId);
        CoinCharge coinCharge = coinChargeRepository.findByUserPageUserPageId(userPage.getUserPageId());

        return coinChargeDetailRepository.findByCoinChargeCoinChargeId(coinCharge.getCoinChargeId());
    }
}
