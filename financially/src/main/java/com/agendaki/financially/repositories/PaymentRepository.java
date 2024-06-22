package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findByIdPreUser(String id);

    void deleteByIdPreUser(String id);

    boolean existsByIdPreUser(String id);

    @Query("{'idPreUser': ?0}")
    void updatePaymentStatusAndDateTransactionByIdPreUser(String id, PaymentStatus status, LocalDate dateTransaction);
}
