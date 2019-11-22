package com.hotel.hotelapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Hotel> getHoteisReservadoCliente(Long idCliente) {

		return hotelRepository.findAll().stream()
				.filter(x -> x.getReservas().stream().anyMatch(y -> y.getCliente().getId().equals(idCliente)))
				.map(x -> {
					x.getReservas().stream().filter(y -> y.getCliente().getId().equals(idCliente));
					return x;
				})
				.collect((Collectors.toList()));
	}

	public BigDecimal getTotalReservas(Long idCliente) {
		List<Hotel> hoteis = getHoteisReservadoCliente(idCliente);
		
		return hoteis
		.stream()
		.map(x -> x.getReservas())
		.flatMap(x -> x.stream())
		.map(Reserva::getPrecoTotal)
		.reduce(BigDecimal::add)
		.orElse(BigDecimal.ZERO);
	}
	
	public BigDecimal getTotalReservaHotel(Long idHotel, Long idCliente) {
		List<Reserva> reservas = getReservasClientePorHotel(idHotel, idCliente);
		
		return reservas
		.stream()
		.map(Reserva::getPrecoTotal)
		.reduce(BigDecimal::add)
		.orElse(BigDecimal.ZERO);
	}
	
	public List<Reserva> getReservasClientePorHotel(Long idHotel, Long idCliente) {

		return hotelRepository
				.findAll()
				.stream()
				.filter(x -> x.getId().equals(idHotel))
				.map(x -> x.getReservas())
				.flatMap(x -> x.stream())
				.filter(x -> x.getCliente().getId().equals(idCliente)).collect((Collectors.toList()));
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