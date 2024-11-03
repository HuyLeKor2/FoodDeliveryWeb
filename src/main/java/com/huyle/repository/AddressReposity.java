package com.huyle.repository;

import com.huyle.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressReposity extends JpaRepository<Address, Long> {
}
