package edu.miu.sa.paymentservice.repository;

import edu.miu.sa.paymentservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PaymentRepository extends JpaRepository<Transaction, Long> {

}
