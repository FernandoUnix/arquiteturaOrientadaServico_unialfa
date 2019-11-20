package com.hotel.hotelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.model.Reserva;
import com.hotel.hotelapi.repository.IHotelRepository;

@Service
public class HotelService {

	@Autowired
	private IHotelRepository hotelRepository;
	
	public Hotel getHotelById(Long id) {
		return hotelRepository.getOne(id);
	}
	
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public Reserva addReserva(Cliente cliente, Hotel hotel) {
		Reserva reserva =  new Reserva();
		reserva.setCliente(cliente);
		
		hotel.adicionarReserva(reserva);
		
		return reserva;
	}
}
