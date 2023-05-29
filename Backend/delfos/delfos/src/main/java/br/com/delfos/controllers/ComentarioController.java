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

import br.com.delfos.entitys.ComentarioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.ComentarioDTO;
import br.com.delfos.entitys.dtos.EmpresaDTO;
import br.com.delfos.services.ComentarioServiceIMPL;
import br.com.delfos.services.excepcions.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("api/comentario")
public class ComentarioController {
	
	
	@Autowired
	private ComentarioServiceIMPL servico;
	
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<ComentarioDTO> salvar( @RequestBody ComentarioDTO dto) {

		ComentarioEntity cliente = new ComentarioEntity(dto);

		try {

			this.servico.salvar(cliente);

		} catch (Exception e) {
			dto.setMenssagem(e.getMessage());

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}
	
	@ApiOperation(value = "Retornar todos do banco de dados")
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> index() {

		List<ComentarioDTO> empresaDTOs = new ArrayList<>();

		List<ComentarioEntity> empresas = this.servico.buscartudo();

		if (!empresas.isEmpty()) {
			empresaDTOs = empresas.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(empresaDTOs);

	}
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ComentarioDTO> show(@PathVariable Long id) {

		Optional<ComentarioEntity> cliente = this.servico.buscarPessoa(id);

		if (cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());

		}

		ComentarioDTO clienteDTO = new ComentarioDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}
	
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<ComentarioDTO> delete(@PathVariable Long id){
		
		ComentarioDTO clienteDTO = new ComentarioDTO();
		
		clienteDTO.setId(id);
		
		try {
		
			this.servico.deletar(clienteDTO.getId());
			clienteDTO.setMenssagem("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
		} catch (Exception e) {
			clienteDTO.setMenssagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		}
	}
	
	
	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioDTO> update(@PathVariable Long id, @RequestBody ComentarioDTO dto) {
		
		Optional<ComentarioEntity> cliente = this.servico.buscarPessoa(id);
		
		if(cliente.isPresent()) {
			try {
				servico.atualizar(id, dto);
			} catch (NotFoundException e) {
				dto.setMenssagem(e.getMessage());

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());
		}
		
		ComentarioDTO clienteDTO = new ComentarioDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		
		
	}
	
	 @GetMapping("/{anuncioId}/comentarios")
	    public ResponseEntity<List<ComentarioEntity>> getComentariosByAnuncioId(@PathVariable Long anuncioId) {
	        List<ComentarioEntity> comentarios = servico.buscarComentariosPorAnuncioId(anuncioId);
	        return ResponseEntity.ok(comentarios);
	    }
	
	
	

}
