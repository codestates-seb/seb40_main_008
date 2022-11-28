package main008.BED.payment.repository;

import main008.BED.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value =
            "SELECT * FROM payment " +
            "WHERE payment.contents_id = :contents_id"
            , nativeQuery = true)
    Optional<Payment> findByContentsId(Long contents_id);
    Optional<Payment> findByPaymentId(Long paymentId);
}
