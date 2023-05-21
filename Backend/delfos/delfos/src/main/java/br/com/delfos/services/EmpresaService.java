package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.EmpresaDTO;
import br.com.delfos.services.excepcions.NotFoundException;

public interface EmpresaService {
 void salvar(EmpresaEntity empresa) throws NotFoundException;
	 
	 List<EmpresaEntity> buscartudo();
	 
	 Optional<EmpresaEntity> buscarPessoa(Long id);
	 
	void atualizar(Long id, EmpresaDTO empresa) throws NotFoundException;
	 
	 void deletar(Long id) throws NotFoundException;

}
