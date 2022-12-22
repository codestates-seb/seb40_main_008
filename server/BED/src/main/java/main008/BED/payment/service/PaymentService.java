package main008.BED.payment.service;

import lombok.RequiredArgsConstructor;
import main008.BED.coinCharge.repository.CoinChargeDetailRepository;
import main008.BED.coinCharge.repository.CoinChargeRepository;
import main008.BED.coinCharge.service.CoinChargeService;
import main008.BED.contents.entity.Contents;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.entity.PaymentDetail;
import main008.BED.payment.repository.PaymentDetailRepository;
import main008.BED.payment.repository.PaymentRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDetailRepository paymentDetailRepository;
    private final UsersRepository usersRepository;
    private final PaymentRepository paymentRepository;
    private final CoinChargeService coinChargeService;

    /**
     * 컨텐츠 개설 시 가격 등록
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createPaymentWithContent(Payment payment) {

        payment.setPaymentDetails(new ArrayList<>());

        verifyUnitPrice(payment.getPrice());

        paymentRepository.save(payment);
    }

    /**
     * 컨텐츠 결제
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void payContent(PaymentDetail paymentDetail, Users users, Contents contents) {

        Payment payment = paymentRepository.findByContentsId(contents.getContentsId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND));

        List<PaymentDetail> paymentDetails = paymentDetailRepository.findByPaymentId(payment.getPaymentId());

        paymentDetail.setUsers(setCoinToUsers(payment, users.getUsersId(), contents));
        paymentDetail.setPayedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        paymentDetail.setPayment(payment);

        paymentDetails.add(paymentDetail);

        payment.setPaymentDetails(paymentDetails);
        paymentRepository.save(payment);

        coinChargeService.setBuyer(users, paymentDetail);
        coinChargeService.setSeller(contents, paymentDetail);
    }

    @Transactional(readOnly = true)
    public List<Payment> getPayContent(Long usersId, MyClass myClass) {

        List<Payment> paymentList = myClass.getPayments();
        List<PaymentDetail> paymentDetails = paymentDetailRepository.findByUsersId(usersId);

        for (PaymentDetail paymentDetail : paymentDetails) {

            Payment payment = findPayment(paymentDetail);

            payment.setMyClass(myClass);
            paymentList.add(paymentRepository.save(payment));
        }

        return paymentList;
    }

    @Transactional(readOnly = true)
    public Payment findPayment(PaymentDetail paymentDetail) {

        return paymentRepository.findByPaymentId(
                paymentDetail
                        .getPayment()
                        .getPaymentId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND));
    }

    /**
     * 가격 등록 시 제한
     */
    private void verifyUnitPrice(Integer price) {

        if (price % 1000 != 0) {
            throw new BusinessLogicException(ExceptionCode.BAD_PRICE);
        } else if (price > 50000) {
            throw new BusinessLogicException(ExceptionCode.OVER_PRICE);
        }
    }

    public Users setCoinToUsers(Payment payment, Long usersId, Contents contents) {

        Users users = usersRepository.findByUsersId(usersId);
        Users tutorUsers = usersRepository.findByUsersId(contents.getUsers().getUsersId());

        verifiedBuyContents(payment, users.getUsersId());

        users.setTotalCoin(users.getTotalCoin() - payment.getPrice());

        verifyCountOfCoin(users.getTotalCoin());

        tutorUsers.setTotalCoin(tutorUsers.getTotalCoin() + payment.getPrice());

        usersRepository.save(tutorUsers);
        return usersRepository.save(users);
    }

    /**
     * 결제 시 코인 부족 확인
     */
    public void verifyCountOfCoin(Integer totalCoin) {

        if (totalCoin < 0) {
            throw new BusinessLogicException(ExceptionCode.COIN_SHORTAGE);
        }
    }

    /**
     * 이미 구매했던 컨텐츠인지 확인
     */
    public void verifiedBuyContents(Payment payment, Long usersId) {

        if (paymentDetailRepository.findBoughtContents(payment.getPaymentId(), usersId) != null) {
            throw new BusinessLogicException(ExceptionCode.DUPLICATE_PAY);
        }
    }

    public boolean verifyPaidByUser(Long contentsId, Long usersId) {

        if (paymentDetailRepository.findBoughtContents(contentsId, usersId) != null) {
            return true;
        } else {
            return false;
        }
    }
}
