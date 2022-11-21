package main008.BED.payment.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /*
    컨텐츠 개설 시 가격 등록
    */
    public Payment createPaymentWithContent(Payment payment) {

        payment.setPaymentDetails(new ArrayList<>());

        verifyUnitPrice(payment.getPrice());

        return paymentRepository.save(payment);
    }

    /*
    가격 등록 시 제한
    */
    private void verifyUnitPrice(Integer price) {

        if (price % 1000 != 0) {
            throw new BusinessLogicException(ExceptionCode.BAD_PRICE);
        } else if (price > 50000) {
            throw new BusinessLogicException(ExceptionCode.OVER_PRICE);
        }
    }
}
