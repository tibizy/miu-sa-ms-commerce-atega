package miu.sa.accountservice.controller;

import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.service.AddressService;
import miu.sa.accountservice.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
//    private final AddressService service;
//
//    public AddressController(AddressService service) {
//        this.service = service;
//    }
//
//    @PostMapping("add")
//    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
//        return ResponseEntity.ok(service.save(address));
//    }
//
//    @PostMapping("addAll")
//    public ResponseEntity<List<Address>> addAddresses(@RequestBody List<Address> addresses) {
//        return ResponseEntity.ok(service.saveAll(addresses));
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<List<Address>> findAll(@PathVariable Long acctId) {
//        return ResponseEntity.ok(service.findAll(acctId));
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<Address> findAddressById(@PathVariable(value = "id") Long id) {
//        return ResponseEntity.ok().body(service.findById(id));
//    }
//
//    @PutMapping("update/{id}")
//    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long id,
//                                                    @RequestBody Address account) {
//        return ResponseEntity.ok(service.update(id, account));
//
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
//        service.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
