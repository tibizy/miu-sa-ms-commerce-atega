package edu.miu.shippingservice.controller;

import edu.miu.shippingservice.model.Shipment;
import edu.miu.shippingservice.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/shipping")
public class ShipmentController{
    @Autowired
    private MyService myService;

    @GetMapping("{id}")
    public ResponseEntity<Shipment> shippingStatus(@PathVariable Long id){
        return ResponseEntity.ok(myService.processOrder(id));
    }

}
