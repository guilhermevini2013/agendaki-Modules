package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findByIdPreUser(String id);

    void deleteByIdPreUser(String id);

    boolean existsByIdPreUser(String id);

    @Query("{'idPreUser': ?0}")
    void updatePaymentStatusAndDateTransactionByIdPreUser(String id, PaymentStatus status, LocalDate dateTransaction);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'pre-user', localField: 'idPreUser', foreignField: '_id', as: 'preUserData' } }",
            "{ $unwind: '$preUserData' }",
            "{ $project: { 'paymentStatus': 1 } }"
    })
    List<PaymentStatusProjection> findAllPaymentStatusProjection();

    interface PaymentStatusProjection {
        String getPaymentStatus();
    }
}
