package br.com.delfos.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.ComentarioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.ComentarioDTO;
import br.com.delfos.repositorys.AnuncioRepository;
import br.com.delfos.repositorys.ComentarioRepository;
import br.com.delfos.services.excepcions.NotFoundException;

@Service
public class ComentarioServiceIMPL implements ComentarioService {
	
	@Autowired
	 private ComentarioRepository Crepository;
	
	@Autowired
	private AnuncioRepository Arpeository;

	@Override
	public void salvar(ComentarioEntity empresa) throws NotFoundException {
		try {
		
			this.Crepository.save(empresa);
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		
	}

	@Override
	public List<ComentarioEntity> buscartudo() {
		try {

			return this.Crepository.findAll();

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public Optional<ComentarioEntity> buscarPessoa(Long id) {
		try {

			return this.Crepository.findById(id);

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public void atualizar(Long id, ComentarioDTO empresa) throws NotFoundException {
	Optional<ComentarioEntity> objComentario = Crepository.findById(id);
		
		if (objComentario.isPresent()) {
			ComentarioEntity obj = objComentario.get();
			
			obj.setAnuncio(empresa.getAnuncio());
			obj.setAvaliacao(empresa.getAvaliacao());
//			obj.setCliente(empresa.getCliente());
			obj.setId(empresa.getId());
			obj.setTexto(empresa.getTexto());
			
				
				Crepository.save(obj);
			}
		
		
		if(objComentario.isEmpty()) {
			
			throw new NotFoundException("Usuario não Cadastrado");
			
		}
		
		
	}

	@Override
	public void deletar(Long id) throws NotFoundException {
	Optional<ComentarioEntity> objEmpresa = Crepository.findById(id);
		
		try {
			if (objEmpresa.isEmpty()) {
				throw new NotFoundException("Usuario não cadastrado.");
			}

			Crepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possivel deletar o usuario.");
		}
		
	}

	@Override
	public List<ComentarioEntity> buscarComentariosPorAnuncioId(Long anuncioId) {
		
		
		return Crepository.findAllByAnuncioId(anuncioId);
		
	}

//	@Override
//	public List<ComentarioEntity> getAnunciosByEmpresa(AnuncioEntity anuncio) {
//		try {
//			return Crepository.findByComentario(anuncio);
//			
//		} catch (Exception e) {
//			return null;
//		}
//	}

	

	
}
