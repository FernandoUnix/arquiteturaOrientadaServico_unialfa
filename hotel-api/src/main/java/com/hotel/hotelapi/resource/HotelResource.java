package com.hotel.hotelapi.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.model.Reserva;
import com.hotel.hotelapi.service.ClienteService;
import com.hotel.hotelapi.service.HotelService;

@RestController
public class HotelResource {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private HotelService hotelService;

	@GetMapping("/helloworld")
	public String helloWorld() {
		return "hello world";
	}

	@GetMapping("/consulta-hotel")
	public List<Hotel> getListHotel() {
		return hotelService.getListHotel();
	}

//	@GetMapping("/reservas-cliente")
//	public List<Reserva> getReservasClientes(Long idCliente) {
//		return clienteService.getClienteById(idCliente).getReservas();
//	}

	@PostMapping("/realizar-reserva")
	public ResponseEntity<Reserva> realizarReserva(@RequestBody Cliente cliente, @RequestParam("idHotel") Long idHotel,
			@RequestParam("inicio")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio, 
			@RequestParam("fim")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

		Cliente cli = clienteService.getOrSaveCliente(cliente);
		Hotel hotel = hotelService.getHotelById(idHotel);

		Reserva reserva = hotelService.addReserva(cli, hotel, inicio, fim);

		return ResponseEntity.ok(reserva);
	}
}
