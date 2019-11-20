package com.hotel.hotelapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cidade;
	private BigDecimal preco;
	private LocalDateTime inicioEstadia;
	private LocalDateTime fimEstadia;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hotel")
	private List<Reserva> reservas = new ArrayList<Reserva>();
}
