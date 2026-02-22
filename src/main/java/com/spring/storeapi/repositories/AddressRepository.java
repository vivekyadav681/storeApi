package com.spring.storeapi.repositories;
import com.spring.storeapi.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}