package com.hotel.hotelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapi.model.Reserva;

public interface IReservaRepository extends JpaRepository<Reserva, Long>{

}
