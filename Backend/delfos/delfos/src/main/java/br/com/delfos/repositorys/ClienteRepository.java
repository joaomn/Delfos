package br.com.delfos.repositorys;


import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.delfos.entitys.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
	
	Optional<ClienteEntity> findUsuarioByEmail(String email);
	Optional<ClienteEntity> findUsuarioByNome(String nome);
	Optional<ClienteEntity> findUsuarioByTelefone(String nome);
	

	

}
