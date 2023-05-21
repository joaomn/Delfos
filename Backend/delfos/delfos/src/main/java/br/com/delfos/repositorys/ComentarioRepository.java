package br.com.delfos.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
	
	Optional<List<ComentarioEntity>> findContatoByCliente(ClienteEntity cliente);

}
