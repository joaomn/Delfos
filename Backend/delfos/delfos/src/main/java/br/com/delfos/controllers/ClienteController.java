package br.com.delfos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
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
import org.springframework.web.util.UriUtils;

import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.dtos.ClienteDTO;
import br.com.delfos.repositorys.ClienteRepository;
import br.com.delfos.services.ClienteServiceIMPL;
import ch.qos.logback.core.net.server.Client;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteServiceIMPL servico;

	@ApiOperation(value = "Persisitr dados no banco")
	@PostMapping
	public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO dto) {

		ClienteEntity cliente = new ClienteEntity(dto);

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
	public ResponseEntity<List<ClienteDTO>> index() {

		List<ClienteDTO> clienteDTO = new ArrayList<>();

		List<ClienteEntity> clientes = this.servico.buscartudo();

		if (!clientes.isEmpty()) {
			clienteDTO = clientes.stream().map(clienteentity -> clienteentity.toDto()).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);

	}

	@ApiOperation(value = "Retornar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> show(@PathVariable Long id) {

		Optional<ClienteEntity> cliente = this.servico.buscarPessoa(id);

		if (cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());

		}

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}

	@ApiOperation(value = "Retornar por email")
	@GetMapping("/alguem/{cliente}")
	public ResponseEntity<ClienteDTO> showbyemail(@PathVariable("cliente") String clienteencod) {

		String email = UriComponentsBuilder.fromUriString(clienteencod).build().getPathSegments().get(0);

		Optional<ClienteEntity> clientee = this.servico.buscarPorEmail(email);

		if (clientee.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(clientee.get().toDto());

		}

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}

	@ApiOperation(value = "Deletar Cadastro")
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {

		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setId(id);

		try {

			this.servico.delete(clienteDTO.getId());
			clienteDTO.setMenssagem("Excluido com sucesso");
			return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
		} catch (Exception e) {
			clienteDTO.setMenssagem(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);
		}
	}

	@ApiOperation(value = "Atualizar cadastro")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Long id, @RequestBody ClienteDTO dto) {

		Optional<ClienteEntity> cliente = this.servico.buscarPessoa(id);

		if (cliente.isPresent()) {
			servico.update(id, dto);

			return ResponseEntity.status(HttpStatus.OK).body(cliente.get().toDto());
		}

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setMenssagem("Usuario não encontrado");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteDTO);

	}

	@PostMapping("/login")
	public ResponseEntity<ClienteDTO> logain(@RequestBody ClienteDTO dto) {
		Optional<ClienteEntity> objCli = repo.findUsuarioByEmail(dto.getEmail());

		if (objCli.isPresent()) {
			servico.loadUserByUsername(objCli.get().getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(dto);

		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	}

	@Autowired
	private ClienteRepository repo;

}
