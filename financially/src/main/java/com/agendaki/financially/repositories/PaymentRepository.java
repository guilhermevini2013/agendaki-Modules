package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.PaymentStatus;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.preuser.TypeSignature;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

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

        public Map<String, Object> toMap() {
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

    @Aggregation(pipeline = {
            "{ $match: { '_id': :#{#id} } }",
            "{ $lookup: { from: 'pre-user', localField: 'idPreUser', foreignField: '_id', as: 'preUserData' } }",
            "{ $unwind: '$preUserData' }",
            "{ $project: { 'paymentStatus': 1, 'typePayment': 1, 'dateOpen': 1, 'preUserData.tradeName': 1, 'preUserData._id': 1, 'preUserData.password': 1, 'preUserData.name': 1, 'preUserData.tellPhone': 1, 'price': 1, 'preUserData.email': 1, 'typeSignature': 1 } }"
    })
    PaymentCompletedProjection getPaymentCompletedProjectionById(@Param("id") ObjectId id);

    record PaymentCompletedProjection(PaymentStatus paymentStatus, TypePayment typePayment, LocalDate dateOpen,
                                      BigDecimal price, PreUserToPaymentCompletedProjection preUserData,
                                      TypeSignature typeSignature) {

        public record PreUserToPaymentCompletedProjection(String _id, String tradeName, String email, String password,
                                                          String name, String tellPhone) {
        }

        public Map<String, Object> toMap() {
            Map<String, Object> result = new HashMap<>();
            result.put("tradeName", this.preUserData.tradeName);
            result.put("name", this.preUserData.name);
            result.put("tellPhone", this.preUserData.tellPhone);
            result.put("password", this.preUserData.password);
            result.put("id", this.preUserData._id);
            result.put("email", this.preUserData.email);
            result.put("dateOpen", new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(this.dateOpen)));
            result.put("price", this.price);
            result.put("typePayment", this.typePayment);
            result.put("paymentStatus", PaymentStatus.PAID.getStatusInPtBr());
            result.put("signatureDescription", this.typeSignature.getInformation().description());
            return result;
        }

    }
}
