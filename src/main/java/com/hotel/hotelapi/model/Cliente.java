package com.hotel.hotelapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 80)
    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
//    @OneToMany(mappedBy = "cliente")
//    private List<Reserva> reservas = new ArrayList<Reserva>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

//	public List<Reserva> getReservas() {
//		return reservas;
//	}
//
//	public void setReservas(List<Reserva> reservas) {
//		this.reservas = reservas;
//	}
    
    
    
}
