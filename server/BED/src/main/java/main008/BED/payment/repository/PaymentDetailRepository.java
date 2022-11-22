package main008.BED.payment.repository;

import main008.BED.payment.entity.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

    List<PaymentDetail> findByPaymentPaymentId(Long paymentId);
    List<PaymentDetail> findByUsersUsersId(Long usersId);
}
