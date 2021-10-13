package com.djff.orders.dot.request;
import lombok.*;

import java.util.List;

@Data
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    @Data
    @Setter@Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class ProductUpdateObject{
        private long productId;
        private int quantity;
    }

    List<ProductUpdateObject> products;

}
