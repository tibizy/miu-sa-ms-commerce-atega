package com.djff.orders.dot.response;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingResponse {
    private UUID orderId;
    private String status;
    private String trackingID;
}
