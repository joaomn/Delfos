package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.dtos.ClienteDTO;
import br.com.delfos.repositorys.ClienteRepository;
import br.com.delfos.services.excepcions.NotFoundException;

@Service
public class ClienteServiceIMPL implements ClienteService, UserDetailsService {

	@Autowired
	ClienteRepository Crepository;

	@Override
	public void salvar(ClienteEntity cliente) throws NotFoundException {

		Optional<ClienteEntity> objemail = this.Crepository.findUsuarioByEmail(cliente.getEmail());

		Optional<ClienteEntity> objnome = this.Crepository.findUsuarioByNome(cliente.getNome());

		Optional<ClienteEntity> objtel = this.Crepository.findUsuarioByTelefone(cliente.getTelefone());

		if (objemail.isPresent()) {
			throw new NotFoundException("Email já Cadastrado");
		}

		if (objnome.isPresent()) {
			throw new NotFoundException("Nome já Cadastrado");
		}

		if (objtel.isPresent()) {
			throw new NotFoundException("Telefone  já Cadastrado");
		}

		
			String senha = new BCryptPasswordEncoder().encode(cliente.getPassword());

			cliente.setPassword(senha);
		

		this.Crepository.save(cliente);

	}

	@Override
	public List<ClienteEntity> buscartudo() {
		try {

			return this.Crepository.findAll();

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public Optional<ClienteEntity> buscarPessoa(Long id) {

		try {
			return this.Crepository.findById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ClienteDTO update(Long id, ClienteDTO clientedto) {
		try {
			Optional<ClienteEntity> objCliente = Crepository.findById(id);
			if (objCliente.isPresent()) {
				ClienteEntity obj = objCliente.get();
				if (clientedto.getNome() != null) {
					obj.setNome(clientedto.getNome());
				}

				if (clientedto.getEmail() != null) {
					obj.setEmail(clientedto.getEmail());
				}

				if (clientedto.getDtNascimento() != null) {
					obj.setDtNascimento(clientedto.getDtNascimento());
				}

				if (clientedto.getTelefone() != null) {
					obj.setTelefone(clientedto.getTelefone());
				}

				Crepository.save(obj);
			}

		} catch (Exception e) {
			return null;
		}

		return null;

	}

	@Override
	public void delete(Long id) throws NotFoundException {

		Optional<ClienteEntity> objCliente = Crepository.findById(id);

		try {
			if (objCliente.isEmpty()) {
				throw new NotFoundException("Usuario não cadastrado.");
			}

			Crepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possivel deletar o usuario.");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ClienteEntity user = Crepository.findUsuarioByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + " Não foi encontrado"));
		return user;
	}

	@Override
	public Optional<ClienteEntity> buscarPorEmail(String cliente) {
		try {
			return this.Crepository.findUsuarioByEmail(cliente);
		} catch (Exception e) {
			return null;
		}
	}

}
