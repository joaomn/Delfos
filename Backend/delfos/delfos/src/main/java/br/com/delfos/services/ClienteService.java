package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.dtos.ClienteDTO;
import br.com.delfos.services.excepcions.NotFoundException;

public interface ClienteService {
	
 void salvar(ClienteEntity cliente) throws NotFoundException;
	 
	 List<ClienteEntity> buscartudo();
	 
	 Optional<ClienteEntity> buscarPessoa(Long id);
	 
	 ClienteDTO update(Long id, ClienteDTO cliente);
	 
	 void delete(Long id) throws NotFoundException;
	 
	 Optional<ClienteEntity> buscarPorEmail (String cliente);
	

}
