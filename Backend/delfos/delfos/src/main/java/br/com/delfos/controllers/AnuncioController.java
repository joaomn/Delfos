package br.com.delfos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.AnuncioDTO;
import br.com.delfos.entitys.dtos.EmpresaDTO;
import br.com.delfos.services.AnuncioServiceIMPL;
import br.com.delfos.services.excepcions.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/anuncio")
public class AnuncioController {
	
	@Autowired
	private AnuncioServiceIMPL servico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<AnuncioDTO> salvar(@Valid @RequestBody AnuncioDTO dto) {

		AnuncioEntity cliente = new AnuncioEntity(dto);

		try {

			this.servico.salvar(cliente);

		} catch (Exception e) {
			dto.setMessagem(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<AnuncioDTO>> index() {

		List<AnuncioDTO> anuncioDTOs = new ArrayList<>();

		List<AnuncioEntity> empresas = this.servico.buscartudo();

		if (!empresas.isEmpty()) {
			anuncioDTOs = empresas.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(anuncioDTOs);

	}
	
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<AnuncioDTO> show(@PathVariable Long id) {

		Optional<AnuncioEntity> cliente = this.servico.buscarPorIdentificador(id);

		if (cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());

		}

		AnuncioDTO clienteDTO = new AnuncioDTO();
		clienteDTO.setMessagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);


	}
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<AnuncioDTO> delete(@PathVariable Long id){
		
		AnuncioDTO clienteDTO = new AnuncioDTO();
		
		clienteDTO.setId(id);
		
		try {
		
			this.servico.deletar(clienteDTO.getId());
			clienteDTO.setMessagem("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
		} catch (Exception e) {
			clienteDTO.setMessagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		}
	}
	
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<AnuncioDTO> update(@PathVariable Long id, @RequestBody AnuncioDTO dto) {
		
		Optional<AnuncioEntity> cliente = this.servico.buscarPorIdentificador(id);
		
		if(cliente.isPresent()) {
			try {
				servico.atualizar(id, dto);
			} catch (NotFoundException e) {
				dto.setMessagem(e.getMessage());

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		
		AnuncioDTO clienteDTO = new AnuncioDTO();
		clienteDTO.setMessagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		
		
	}
	
	@ApiOperation(value = "Retornar anuncio por titulo")
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<AnuncioDTO>> retornarPorTitulo(@PathVariable String titulo) {

		List<AnuncioDTO> anuncioDTOs = new ArrayList<>();

		List<AnuncioEntity> empresas = this.servico.buscarPorTitulo(titulo);

		if (!empresas.isEmpty()) {
			anuncioDTOs = empresas.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(anuncioDTOs);

	}
	
	@ApiOperation(value = "Retornar anuncio por tipo")
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<AnuncioDTO>> retornarPorTipo(@PathVariable String tipo) {

		List<AnuncioDTO> anuncioDTOs = new ArrayList<>();

		List<AnuncioEntity> empresas = this.servico.buscarPorTipo(tipo);

		if (!empresas.isEmpty()) {
			anuncioDTOs = empresas.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(anuncioDTOs);

	}
	
	 
	
	

}
