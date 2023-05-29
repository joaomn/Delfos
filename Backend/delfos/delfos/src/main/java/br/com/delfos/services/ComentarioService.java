package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.ComentarioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.ComentarioDTO;
import br.com.delfos.services.excepcions.NotFoundException;

public interface ComentarioService  {
	
 void salvar(ComentarioEntity empresa) throws NotFoundException;
	 
	 List<ComentarioEntity> buscartudo();
	 
	 Optional<ComentarioEntity> buscarPessoa(Long id);
	 
	void atualizar(Long id, ComentarioDTO empresa) throws NotFoundException;
	 
	 void deletar(Long id) throws NotFoundException;
	 
	 List<ComentarioEntity> buscarComentariosPorAnuncioId(Long anuncioId);
	 
//	 List<ComentarioEntity> getAnunciosByEmpresa(AnuncioEntity anuncio);

}
