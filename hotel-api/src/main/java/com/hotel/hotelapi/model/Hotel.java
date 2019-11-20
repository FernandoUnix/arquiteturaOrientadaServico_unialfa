package com.hotel.hotelapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hotel.hotelapi.enums.HotelStatus;

@Entity
public class Hotel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cidade;
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private HotelStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hotel")
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	public void adicionarReserva(Reserva reserva) {
		reserva.setHotel(this);
		reservas.add(reserva);
	}

	public void removerPedido(Reserva reserva) {
		reservas.remove(reserva);
	}
}
