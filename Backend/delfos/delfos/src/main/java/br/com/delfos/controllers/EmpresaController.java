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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.ClienteDTO;
import br.com.delfos.entitys.dtos.EmpresaDTO;
import br.com.delfos.repositorys.EmpresaRepository;
import br.com.delfos.services.AnuncioServiceIMPL;
import br.com.delfos.services.EmpresaServiceIMPL;
import br.com.delfos.services.excepcions.NotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceIMPL servico;
	
	@Autowired
	private AnuncioServiceIMPL Aservico;
	
	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<EmpresaDTO> salvar(@Valid @RequestBody EmpresaDTO dto) {

		EmpresaEntity cliente = new EmpresaEntity(dto);

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
	public ResponseEntity<List<EmpresaDTO>> index() {

		List<EmpresaDTO> empresaDTOs = new ArrayList<>();

		List<EmpresaEntity> empresas = this.servico.buscartudo();

		if (!empresas.isEmpty()) {
			empresaDTOs = empresas.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(empresaDTOs);

	}
	
	
	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<EmpresaDTO> show(@PathVariable Long id) {

		Optional<EmpresaEntity> cliente = this.servico.buscarPessoa(id);

		if (cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());

		}

		EmpresaDTO clienteDTO = new EmpresaDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}
	
	@ApiOperation(value = "Retornar por email")
	@GetMapping("/alguem/{cliente}")
	public ResponseEntity<EmpresaDTO> showbyemail(@PathVariable("cliente") String clienteencod) {

		String email = UriComponentsBuilder.fromUriString(clienteencod).build().getPathSegments().get(0);

		Optional<EmpresaEntity> clientee = this.servico.buscarPorEmail(email);

		if (clientee.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(clientee.get().toDto());

		}

		EmpresaDTO clienteDTO = new EmpresaDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}
	
	
	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<EmpresaDTO> delete(@PathVariable Long id){
		
		EmpresaDTO clienteDTO = new EmpresaDTO();
		
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
	public ResponseEntity<EmpresaDTO> update(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
		
		Optional<EmpresaEntity> cliente = this.servico.buscarPessoa(id);
		
		if(cliente.isPresent()) {
			try {
				servico.atualizar(id, dto);
			} catch (NotFoundException e) {
				dto.setMenssagem(e.getMessage());

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());
		}
		
		EmpresaDTO clienteDTO = new EmpresaDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		
		
	}
	
	@PostMapping("/login")
	 public ResponseEntity<EmpresaDTO> logain(@RequestBody EmpresaDTO dto){
		Optional<EmpresaEntity> objCli = repo.findContatoByEmail(dto.getEmail());
		
		if(objCli.isPresent()){
			servico.loadUserByUsername(objCli.get().getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(dto);
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
	@ApiOperation(value = "retornar todos anuncios da empresa")
	 @GetMapping("/{empresaId}/anuncios")
	    public ResponseEntity<List<AnuncioEntity>> getAnunciosByEmpresa(@PathVariable Long empresaId) {
	        EmpresaEntity empresa = servico.buscarPessoa(empresaId).orElse(null);
	      
	        if (empresa != null) {
	        	List<AnuncioEntity> anuncios = Aservico.getAnunciosByEmpresa(empresa);
	            
	            return ResponseEntity.status(HttpStatus.OK).body(anuncios);
	            
	        } else {
	           
	        	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	 }
	
	@Autowired
	private EmpresaRepository repo;
	

}
