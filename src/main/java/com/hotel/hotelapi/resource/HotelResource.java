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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotelapi.enums.HotelStatus;
import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.model.Hotel;
import com.hotel.hotelapi.model.ParametrosReserva;
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

//	@ApiOperation(value = "Pagina hello world")
//	@GetMapping("/")
//	public String helloWorld() {
//		return "Ola bem vindo a api-hotel";
//	}

	@ApiOperation(value = "Consultar todos hoteis disponíveis")
	@GetMapping("/api/hotel/consulta")
	public List<Hotel> getListHotel() {
		return hotelService.getListHotel();
	}

	@ApiOperation(value = "Alterar status das reservas pendentes do cliente")
	@PutMapping("/api/hotel/Reserva/cliente/{id}/status/{status}")
	public void atualizaStatusReservas(@PathVariable("id") Long clienteId,
			@PathVariable("status") int status) {
		
		hotelService.atualizaStatusReservas(clienteId, HotelStatus.Status(status));
	}
 
	@ApiOperation(value = "Realizar reserva para um cliente")
	@PostMapping("/api/hotel/realizar-reserva")
	public ResponseEntity<Reserva> realizarReserva(@RequestBody ParametrosReserva parametrosReserva) {

		Cliente cli = clienteService.getOrSaveCliente(parametrosReserva.getCliente());
		Hotel hotel = hotelService.getHotelById(parametrosReserva.getIdHotel());

		Reserva reserva = hotelService.addReserva(cli, hotel, parametrosReserva.getInicio(),
				parametrosReserva.getFim());

		return ResponseEntity.ok(reserva);
	}

	@ApiOperation(value = "Obter reservas de um cliente")
	@GetMapping("/api/hotel/reservas/cliente/{id}")
	public List<Reserva> getHoteisReservadoCliente(@PathVariable("id") Long id) {
		return hotelService.getReservasCliente(id);
	}

//	@ApiOperation(value = "Obter reservas do cliente por hotel")
//	@GetMapping("/api/hotel/reservas/cliente/{idCliente}/hotel/{idHotel}")
//	public List<Reserva> getReservasClientePorHotel(@PathVariable("idHotel") Long idHotel,
//			@PathVariable("idCliente") Long idCliente) {
//		return hotelService.getReservasClientePorHotel(idHotel, idCliente);
//	}

	@ApiOperation(value = "Obter total das reservas pendente de um cliente")
	@GetMapping("/api/hotel/total-reserva-pendente/cliente/{id}")
	public BigDecimal getTotalReservas(@PathVariable("id") Long id) {
		return hotelService.getTotalReservas(id);
	}

//	@ApiOperation(value = "Obter total da reserva do cliente de um determinado hotel")
//	@GetMapping("/api/hotel/total-reserva/cliente/{idCliente}/hote/{idHotel}")
//	public BigDecimal getTotalReservaHotel(@PathVariable("idHotel") Long idHotel,
//			@PathVariable("idCliente") Long idCliente) {
//		return hotelService.getTotalReservaHotel(idHotel, idCliente);
//	}
}
