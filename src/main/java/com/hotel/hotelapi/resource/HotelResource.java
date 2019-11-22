package com.hotel.hotelapi.resource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.model.Reserva;
import com.hotel.hotelapi.service.ClienteService;
import com.hotel.hotelapi.service.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "hotel")
public class HotelResource {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private HotelService hotelService;

	@ApiOperation(value = "Pagina hello world" )
	@GetMapping("/")
	public String helloWorld() {
		return "Ola bem vindo a api-hotel";
	}

	@ApiOperation(value = "Consultar todos hoteis disponíveis" )
	@GetMapping("/api/hotel/consulta")
	public List<Hotel> getListHotel() {
		return hotelService.getListHotel();
	}

	@ApiOperation(value = "Realizar reserva para um cliente" )
	@PostMapping("/api/hotel/{idHotel}/realizar-reserva/inicio/{inicio}/fim/{fim}")
	public ResponseEntity<Reserva> realizarReserva(@RequestBody Cliente cliente, @PathVariable("idHotel") Long idHotel,
			@PathVariable("inicio")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio, 
			@PathVariable("fim")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {

		Cliente cli = clienteService.getOrSaveCliente(cliente);
		Hotel hotel = hotelService.getHotelById(idHotel);

		Reserva reserva = hotelService.addReserva(cli, hotel, inicio, fim);

		return ResponseEntity.ok(reserva);
	}
	
	@ApiOperation(value = "Obter hoteis reservado para o cliente" )
	@GetMapping("/api/hotel/reservas/cliente/{id}")
	public List<Hotel> getHoteisReservadoCliente(@PathVariable("id") Long id) {
		return hotelService.getHoteisReservadoCliente(id);
	}
	
	@ApiOperation(value = "Obter reservas do cliente por hotel" )
	@GetMapping("/api/hotel/reservas/cliente/{idCliente}/hotel/{idHotel}")
	public List<Reserva> getReservasClientePorHotel(@PathVariable("idHotel") Long idHotel, @PathVariable("idCliente") Long idCliente) {
		return hotelService.getReservasClientePorHotel(idHotel, idCliente);
	}

	@ApiOperation(value = "Obter total da reserva do cliente" )
	@GetMapping("/api/hotel/total-reserva/cliente/{idCliente}")
	public BigDecimal getTotalReservas(@PathVariable("idCliente") Long idCliente) {
		return hotelService.getTotalReservas(idCliente);
	}
	
	@ApiOperation(value = "Obter total da reserva do cliente de um determinado hotel" )
	@GetMapping("/api/hotel/total-reserva/cliente/{idCliente}/hote/{idHotel}")
	public BigDecimal getTotalReservaHotel(@PathVariable("idHotel") Long idHotel, @PathVariable("idCliente") Long idCliente) {
		return hotelService.getTotalReservaHotel(idHotel, idCliente);
	}
}
