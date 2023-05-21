package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import br.com.delfos.entitys.AnuncioEntity;

import br.com.delfos.entitys.dtos.AnuncioDTO;

import br.com.delfos.services.excepcions.NotFoundException;

public interface AnuncioService {

	void salvar(AnuncioEntity anuncio, Long id) throws NotFoundException;

	List<AnuncioEntity> buscartudo();

	Optional<AnuncioEntity> buscarPorIdentificador(Long id);

	void atualizar(Long id, AnuncioDTO anuncio) throws NotFoundException;

	void deletar(Long id) throws NotFoundException;
	
	void contarVotos(int numero, Long idAnuncio, Long idEmpresa);

}
