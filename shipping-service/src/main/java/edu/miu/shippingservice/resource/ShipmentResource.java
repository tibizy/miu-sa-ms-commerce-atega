package edu.miu.shippingservice.resource;

import edu.miu.shippingservice.model.Shipment;
import edu.miu.shippingservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/shipment")
public class ShipmentResource {
    @Autowired
    ShipmentRepository shipmentRepository;
    @GetMapping(value = "/all")
    public List<Shipment> getAll(){
        return shipmentRepository.findAll();
    }
    @PostMapping(value = "/load")
    public List<Shipment> persistAddress(@RequestBody final Shipment shipment){
        shipmentRepository.save(shipment);
        return shipmentRepository.findAll();
    }
}
