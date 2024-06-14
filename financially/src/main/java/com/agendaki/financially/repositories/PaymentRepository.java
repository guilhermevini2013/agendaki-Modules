package com.agendaki.financially.repositories;

import com.agendaki.financially.models.payment.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findByIdPreUser(String id);
}
