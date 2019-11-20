package com.hotel.hotelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotelapi.model.Hotel;

public interface IHotelRepository extends JpaRepository<Hotel, Long>{

}
