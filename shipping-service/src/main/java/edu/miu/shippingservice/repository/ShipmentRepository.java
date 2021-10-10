package edu.miu.shippingservice.repository;


import edu.miu.shippingservice.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
}
