package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.entity.PaymentDetail;
import main008.BED.payment.repository.PaymentDetailRepository;
import main008.BED.payment.repository.PaymentRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MyClassService {

    private final MyClassRepository myClassRepository;
    private final WishRepository wishRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentDetailRepository paymentDetailRepository;

    /*
    내가 찜한 컨텐츠
    */
    public MyClass getWishClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersUsersId(usersId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<Wish> wishes = wishRepository.findByMyClassMyClassIdAndWishedTrue(myClass.getMyClassId());

        myClass.setWishes(wishes);

        return myClassRepository.save(myClass);
    }

    /*
    내가 구매한 컨텐츠 (수강중인 컨텐츠)
    */
    public MyClass getBuyClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersUsersId(usersId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<PaymentDetail> paymentDetails = paymentDetailRepository.findByUsersUsersId(usersId);
        List<Payment> payments = myClass.getPayments();

        for (PaymentDetail paymentDetail : paymentDetails) {

            Payment payment = paymentRepository.findByPaymentId(paymentDetail.getPayment().getPaymentId());
            payment.setMyClass(myClass);
            paymentRepository.save(payment);

            payments.add(payment);
        }

        myClass.setPayments(payments);
        return myClassRepository.save(myClass);
    }
}
