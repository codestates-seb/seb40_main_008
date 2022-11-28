package main008.BED.payment.repository;

import main008.BED.payment.entity.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

    @Query(value =
            "SELECT * FROM payment_detail p " +
            "WHERE p.payment_id = :payment_id"
            , nativeQuery = true)
    List<PaymentDetail> findByPaymentId(Long payment_id);

    @Query(value =
            "SELECT * FROM payment_detail p " +
            "WHERE p.users_id = :users_id"
            , nativeQuery = true)
    List<PaymentDetail> findByUsersId(Long users_id);

    @Query(value =
            "SELECT * FROM payment_detail p " +
            "WHERE p.payment_id = :payment_id " +
            "AND p.users_id = :users_id " +
            "AND p.payed = true"
            , nativeQuery = true)
    PaymentDetail findBoughtContents(Long payment_id, Long users_id);
}
