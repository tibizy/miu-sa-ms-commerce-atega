package miu.sa.accountservice.model.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.sa.accountservice.enums.PaymentType;
import miu.sa.accountservice.model.BankPayment;
import miu.sa.accountservice.model.CreditCardPayment;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentType type;
    @Column(columnDefinition = "json")
    private String metaData;
    private boolean isDefault;

    public Object getMetaData() {
        Gson gson=new Gson();
        PaymentType p = getType();
        switch (p) {
            case BANK:  return gson.fromJson(metaData, BankPayment.class);
            case CC: return gson.fromJson(metaData, CreditCardPayment.class);
            default: return metaData;
        }
    }

    public void setMetaData(Object metaData) {
        Gson gson=new Gson();
//        System.out.println("before set:: " + metaData);
        this.metaData = gson.toJson(metaData);
    }
}
