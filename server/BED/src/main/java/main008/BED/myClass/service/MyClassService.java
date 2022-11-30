package main008.BED.myClass.service;

import lombok.RequiredArgsConstructor;
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
    @Transactional(readOnly = true)
    public MyClass getWishClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<Wish> wishes = wishRepository.findByMyClassIdAndTrue(myClass.getMyClassId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        myClass.setWishes(wishes);

        return myClassRepository.save(myClass);
    }

    /*
    내가 구매한 컨텐츠 (수강중인 컨텐츠)
    */
    @Transactional(readOnly = true)
    public MyClass getBuyClass(Long usersId) {

        MyClass myClass = myClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<PaymentDetail> paymentDetails = paymentDetailRepository.findByUsersId(usersId);

        List<Payment> paymentList = myClass.getPayments();

        for (PaymentDetail paymentDetail : paymentDetails) {

            Payment payment = paymentRepository.findByPaymentId(
                    paymentDetail.getPayment().getPaymentId()).orElseThrow(()
                    -> new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND));

            payment.setMyClass(myClass);
            paymentRepository.save(payment);

            paymentList.add(payment);
        }

        myClass.setPayments(paymentList);
        return myClassRepository.save(myClass);
    }

    /**
     * 콘텐츠 상세화면 찜 여부 판단
     */
    public boolean isWished(Long usersId, Long contentsId) {
        MyClass myClass = myClassRepository.findByUsersId(usersId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<Wish> wishes = myClass.getWishes();
        boolean wished = wishes
                        .stream()
                        .anyMatch(wish -> wish.getWished() && wish.getContents().getContentsId() == contentsId);

        if (wished) {
            return true;
        } else {
            return false;
        }
    }
}
