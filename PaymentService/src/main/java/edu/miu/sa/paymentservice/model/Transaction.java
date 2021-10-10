package edu.miu.sa.paymentservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

//@Entity
@Table(name = "Transactions")
@Data
public class Transaction {
    @Id
    public Long id;
    public Long customerReference;
    public String paymentReference;
    public String orderNumber;
    public Double amount;
    public PaymentType type;
    public PaymentStatus status;
    public LocalDateTime requestTime;
    public String responseCode;
    public LocalDateTime responseTime;
    public LocalDateTime dateCreated;

}
