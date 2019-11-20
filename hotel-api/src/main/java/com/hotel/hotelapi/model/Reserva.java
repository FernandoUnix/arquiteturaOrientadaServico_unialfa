package com.hotel.hotelapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reserva implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime inicioEstadia;
	private LocalDateTime fimEstadia;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInicioEstadia() {
		return inicioEstadia;
	}

	public void setInicioEstadia(LocalDateTime inicioEstadia) {
		this.inicioEstadia = inicioEstadia;
	}

	public LocalDateTime getFimEstadia() {
		return fimEstadia;
	}

	public void setFimEstadia(LocalDateTime fimEstadia) {
		this.fimEstadia = fimEstadia;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
