package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.preuser.TypeSignature;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            "{ $project: { 'paymentStatus': 1,'typePayment': 1, 'dateOpen': 1, 'preUserData.tradeName': 1,'price': 1,'preUserData.email': 1, 'typeSignature': 1 } }"
    })
    List<PaymentStatusProjection> findAllPaymentStatusProjection();

    record PaymentStatusProjection(PaymentStatus paymentStatus, TypePayment typePayment, LocalDate dateOpen,
                                   BigDecimal price, PreUserToPaymentStatusProjection preUserData,
                                   TypeSignature typeSignature) {

        public record PreUserToPaymentStatusProjection(String tradeName, String email) {

        }

        public Map<String, Object> toMapPaymentStatus() {
            Map<String, Object> result = new HashMap<>();
            result.put("tradeName", this.preUserData.tradeName);
            result.put("dateOpen", new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(this.dateOpen)));
            result.put("price", this.price);
            result.put("typePayment", this.typePayment);
            result.put("paymentStatus", this.paymentStatus.getStatusInPtBr());
            result.put("signatureDescription", this.typeSignature.getInformation().description());
            return result;
        }
    }
}
