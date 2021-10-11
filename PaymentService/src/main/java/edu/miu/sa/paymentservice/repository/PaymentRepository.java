package edu.miu.sa.paymentservice.repository;

import edu.miu.sa.paymentservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Transaction, Long> {

}
