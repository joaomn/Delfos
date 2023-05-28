package br.com.delfos.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.EmpresaEntity;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioEntity, Long> {

	Optional<AnuncioEntity> findAlgumById(Long id);

	List<AnuncioEntity> findAlgumByTitulo(String string);

	List<AnuncioEntity> findAlgumByTipo(String string);
	
	 List<AnuncioEntity> findByEmpresa(EmpresaEntity empresa);
	

//	List<AnuncioEntity> buscarPorTitulo(String string);
//
//	List<AnuncioEntity> buscarPorTipo(String string);

}
