package edu.miu.shippingservice.service;

import edu.miu.shippingservice.model.Shipment;
import edu.miu.shippingservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class MyService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    public String getTrackingNumber() {
        return "DHL"+ MyService.gen();
    }

    private String trackingNumber;

    public MyService(){
    }

    public static int gen(){
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    public Shipment processOrder(Long Id){
        Shipment shipment = new Shipment();
        shipment.setOrderId(Id);
        shipment.setTrackingNumber(getTrackingNumber());
        shipment.setStatus("shipped");
        return shipmentRepository.save(shipment);
    }

    public String getResponse(){
        return "shipment successful!";
    }

}
