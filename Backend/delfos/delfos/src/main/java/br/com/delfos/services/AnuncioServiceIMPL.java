package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.AnuncioDTO;
import br.com.delfos.repositorys.AnuncioRepository;
import br.com.delfos.repositorys.EmpresaRepository;
import br.com.delfos.services.excepcions.NotFoundException;

@Service
public class AnuncioServiceIMPL  implements AnuncioService{
	
	@Autowired
	private AnuncioRepository Arepository;
	
	@Autowired
	private EmpresaRepository Erepository;

	@Override
	public void salvar(AnuncioEntity anuncio, Long id) throws NotFoundException {
		
		Optional<AnuncioEntity> objId = this.Arepository.findAlgumById(id);
		
		if(objId.isPresent()) {
			throw new NotFoundException("Anuncio  já Cadastrado");
		}
		
		this.Arepository.save(anuncio);
		
		
	}

	@Override
	public List<AnuncioEntity> buscartudo() {
		try {

			return this.Arepository.findAll();

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public Optional<AnuncioEntity> buscarPorIdentificador(Long id) {
		try {

			return this.Arepository.findById(id);

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public void atualizar(Long id, AnuncioDTO anuncio) throws NotFoundException {
		
		Optional<AnuncioEntity> objAnuncio = Arepository.findById(id);
		
		if (objAnuncio.isPresent()) {
			AnuncioEntity obj = objAnuncio.get();
			
			obj.setEmail(anuncio.getEmail());
			obj.setTelefone(anuncio.getTelefone());
			obj.setTexto(anuncio.getTexto());
			obj.setTitulo(anuncio.getTitulo());
			obj.setValor(anuncio.getValor());
			obj.setAvaliacao(anuncio.getAvaliacao());
			
			
				
				
				Arepository.save(obj);
			}
		
		
		if(objAnuncio.isEmpty()) {
			
			throw new NotFoundException("Usuario não Cadastrado");
			
		}
		
	}

	@Override
	public void deletar(Long id) throws NotFoundException {
	
	Optional<AnuncioEntity> objAnuncio = Arepository.findById(id);
		
		try {
			if (objAnuncio.isEmpty()) {
				throw new NotFoundException("Usuario não cadastrado.");
			}

			Arepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possivel deletar o anuncio.");
		}
		
	}

	@Override
	public void contarVotos(int numero, Long idAnuncio, Long idEmpresa) {
		
		Optional<AnuncioEntity> objAnuncio = this.Arepository.findById(idAnuncio);
		
		if(objAnuncio.isPresent()) {
			Optional<EmpresaEntity> objEmpresa = this.Erepository.findById(idEmpresa);
			if(objEmpresa.isPresent()) {
				
				AnuncioDTO obj = new AnuncioDTO();
				
				Long id = objAnuncio.get().getId();
				
				int avaliacaoInicial = objAnuncio.get().getAvaliacao();
				
				
				int porcentagemAvaliacao = (numero / 5) * 100;
				
				
				int avaliacaoFinal = (avaliacaoInicial + porcentagemAvaliacao ) / 2;
				
				if(numero == 0) {
					avaliacaoFinal = porcentagemAvaliacao;
				}
				
				obj.setId(id);
				obj.setAvaliacao(avaliacaoFinal);
				
				try {
					this.atualizar(id, obj);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
			}
		}
		
	}

}
