package dot.request;

import java.util.List;

public class OrderRequest {
    private Long customerId;
    private String paymentType;
    private List<ProductRequest> productRequests;

    public OrderRequest(){}
    public OrderRequest(Long customerId, List<ProductRequest> productRequests) {
        this.customerId = customerId;
        this.productRequests = productRequests;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
