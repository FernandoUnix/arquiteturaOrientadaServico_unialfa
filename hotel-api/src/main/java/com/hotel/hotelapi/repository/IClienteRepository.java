package com.hotel.hotelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapi.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
