package br.com.delfos.repositorys;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import br.com.delfos.entitys.AnuncioEntity;

@Repository
public interface AnuncioRepository  extends JpaRepository<AnuncioEntity, Long>{
	
	

	Optional<AnuncioEntity> findAlgumById (Long id);
	

}
