package miu.sa.accountservice.repository;

import miu.sa.accountservice.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
//    List<Address> findAllByAccountId(Long accountId);
//    Optional<Address> findByAccountIdAndDefault(Long aLong, boolean b);
}
