package edu.miu.shippingservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shipment {
    public Shipment() {
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTrackingNumber() {
        return TrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        TrackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Id
    @Column(name = "orderID")
    private Long orderId;
    @Column(name = "trackingNumber")
    private String TrackingNumber;
    @Column(name = "status")
    private String status;






}
