package br.com.delfos.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.ComentarioEntity;
import br.com.delfos.entitys.EmpresaEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
	
//	Optional<List<ComentarioEntity>> findContatoByCliente(ClienteEntity cliente);
	
//	List<ComentarioEntity> findComentarioByEmpresa(EmpresaEntity empresa);
	
	List<ComentarioEntity> findAllByAnuncioId(Long anuncioId);
	
//	List<ComentarioEntity> findByComentario(AnuncioEntity anuncio);
	

	
	

}
