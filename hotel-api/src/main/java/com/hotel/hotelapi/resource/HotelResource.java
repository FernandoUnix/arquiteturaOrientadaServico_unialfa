package com.hotel.hotelapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/realizar-reserva")
	public Reserva realizarReserva(Cliente cliente, Hotel hotel) {

		Cliente cli = clienteService.getOrSaveCliente(cliente);
		Reserva reserva = hotelService.addReserva(cli, hotel);

		hotelService.save(hotel);
		
		return reserva;
	}
}
