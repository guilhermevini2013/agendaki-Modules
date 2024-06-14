package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
