package com.hotel.hotelapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelapi.enums.HotelStatus;
import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.model.Reserva;
import com.hotel.hotelapi.repository.IHotelRepository;

@Service
public class HotelService {

	@Autowired
	private IHotelRepository hotelRepository;

	public List<Hotel> getListHotel() {
		return hotelRepository.findAll();
	}

	public Hotel getHotelById(Long id) {
		return hotelRepository.getOne(id);
	}

	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Reserva addReserva(Cliente cliente, Hotel hotel, LocalDateTime inicio, LocalDateTime fim) {

		Reserva reserva = new Reserva();

		reserva.setCliente(cliente);
		reserva.setStatus(HotelStatus.ATIVO);
		reserva.setInicioEstadia(inicio);
		reserva.setFimEstadia(fim);

		Period intervalPeriod = Period.between(inicio.toLocalDate(), fim.toLocalDate());
		int quantidadeDias = intervalPeriod.getDays();

		reserva.setPrecoTotal(BigDecimal.valueOf((quantidadeDias * hotel.getPreco().doubleValue())));

		hotel.adicionarReserva(reserva);
		hotelRepository.save(hotel);

		return reserva;
	}
}