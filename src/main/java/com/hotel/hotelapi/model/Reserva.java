package com.hotel.hotelapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hotel.hotelapi.enums.HotelStatus;

@Entity
public class Reserva implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime inicioEstadia;
	private LocalDateTime fimEstadia;
	
	private BigDecimal precoTotal;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private HotelStatus status;
	
	public HotelStatus getStatus() {
		return status;
	}

	public void setStatus(HotelStatus status) {
		this.status = status;
	}

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