package miu.sa.accountservice.service;

import miu.sa.accountservice.exceptions.ResourceNotFoundException;
import miu.sa.accountservice.model.entity.Address;
import miu.sa.accountservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
//    private final AddressRepository repository;
//
//    public AddressService(AddressRepository repository) {
//        this.repository = repository;
//    }
//
//    public Address save(Address address) {
//        return repository.save(address);
//    }
//
//    public List<Address> saveAll(List<Address> addresses) {
//        return repository.saveAll(addresses);
//    }
//
//    public List<Address> findAll(Long acctId) {
//        return repository.findAllByAccountId(acctId);
//    }
//
//    public Address findById(Long id) {
//        return repository.findById(id)
//                .orElseThrow(
//                        () -> new ResourceNotFoundException("Address " + id + " not found")
//                );
//    }
//
//    public Address update(Long id, Address address) {
//        Address add = repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
//
//        add.setStreet(address.getStreet());
//        add.setZip(address.getZip());
//        add.setCity(address.getCity());
//        add.setState(address.getState());
//        return save(add);
//    }
//
//    public void delete(Long id) {
//        Address account = repository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Address " + id + " not found")
//        );
//        repository.delete(account);
//    }
//
//    public List<Address> getAccountAddresses(Long acctId) {
//        return repository.findAllByAccountId(acctId);
//    }

//    public Address updateDefault(Long id) {
//        Address newAdd = repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
//
//        Address add = repository.findByAccountIdAndDefault(newAdd.getAccountId(), true).get();
//        add.setDefault(false);
//
//        newAdd.setDefault(true);
//        return save(add);
//    }
}
