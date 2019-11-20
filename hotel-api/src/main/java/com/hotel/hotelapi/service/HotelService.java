package com.hotel.hotelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelapi.repository.IHotelRepository;

@Service
public class HotelService {

	@Autowired
	private IHotelRepository hotelRepository;
	
}
