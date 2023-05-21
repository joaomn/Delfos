package br.com.delfos.repositorys;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.delfos.entitys.EmpresaEntity;

@Service
public interface EmpresaRepository  extends JpaRepository<EmpresaEntity, Long>{
	
	Optional<EmpresaEntity> findContatoByRazaoSocial(String string);
	Optional<EmpresaEntity> findContatoByCpfCnpj(String string);
	Optional<EmpresaEntity> findContatoByEmail(String string);

}
