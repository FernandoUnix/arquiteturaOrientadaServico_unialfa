package com.hotel.hotelapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.hotelapi.model.Cliente;
import com.hotel.hotelapi.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	public Cliente getOrSaveCliente(Cliente cliente) {
		Cliente clienteRetorno = getClienteById(cliente.getId());

		if (clienteRetorno == null) {
			clienteRetorno = clienteRepository.save(cliente);
		}

		return clienteRetorno;
	}

	public Cliente getClienteById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
}
