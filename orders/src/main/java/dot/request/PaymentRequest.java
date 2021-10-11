package dot.request;

import java.util.UUID;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    public enum PaymentType {
        CARD, BANK
    }

    public Long customerReference;
    public UUID orderNumber;
    public Double amount;
    public PaymentType type;
}
