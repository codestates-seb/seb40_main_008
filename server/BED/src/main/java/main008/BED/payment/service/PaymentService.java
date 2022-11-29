package main008.BED.payment.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.entity.PaymentDetail;
import main008.BED.payment.repository.PaymentDetailRepository;
import main008.BED.payment.repository.PaymentRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
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
    private final PaymentRepository paymentRepository;
    private final ContentsRepository contentsRepository;
    private final UsersRepository usersRepository;

    /**
     * 컨텐츠 개설 시 가격 등록
     */
    public void createPaymentWithContent(Payment payment) {

        payment.setPaymentDetails(new ArrayList<>());

        verifyUnitPrice(payment.getPrice());

        paymentRepository.save(payment);
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

    /**
     * 컨텐츠 결제
     */
    public void payContent(PaymentDetail paymentDetail, Long userId, Long contentsId) {

        Payment payment = paymentRepository.findByContentsId(contentsId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND));

        List<PaymentDetail> paymentDetails = paymentDetailRepository.findByPaymentId(payment.getPaymentId());

        Contents contents = contentsRepository.findByContentsId(contentsId);
        Users buyUsers = usersRepository.findByUsersId(userId);
        Users tutorUsers = usersRepository.findByUsersId(contents.getUsers().getUsersId());

        verifiedBuyContents(payment, userId);

        buyUsers.setTotalCoin(buyUsers.getTotalCoin() - payment.getPrice());

        verifyCountOfCoin(buyUsers.getTotalCoin());

        tutorUsers.setTotalCoin(tutorUsers.getTotalCoin() + payment.getPrice());

        usersRepository.save(tutorUsers);
        usersRepository.save(buyUsers);

        paymentDetail.setPayment(payment);
        paymentDetail.setPayedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        paymentDetail.setUsers(buyUsers);

        paymentDetails.add(paymentDetail);

        payment.setPaymentDetails(paymentDetails);
        paymentRepository.save(payment);
    }

    /**
     * 결제 시 코인 부족 확인
     */
    private void verifyCountOfCoin(Integer totalCoin) {

        if (totalCoin < 0) {
            throw new BusinessLogicException(ExceptionCode.COIN_SHORTAGE);
        }
    }

    /**
     * 이미 구매했던 컨텐츠인지 확인
     */
    private void verifiedBuyContents(Payment payment, Long usersId) {

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
