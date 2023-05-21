package br.com.delfos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delfos.entitys.EmpresaEntity;
import br.com.delfos.entitys.dtos.EmpresaDTO;
import br.com.delfos.repositorys.EmpresaRepository;
import br.com.delfos.services.excepcions.NotFoundException;

@Service
public class EmpresaServiceIMPL implements EmpresaService {
	
	@Autowired
	EmpresaRepository Erepository;

	@Override
	public void salvar(EmpresaEntity empresa) throws NotFoundException {
		
		Optional<EmpresaEntity> objEmail = this.Erepository.findContatoByEmail(empresa.getEmail());
		Optional<EmpresaEntity> objRazaoSocial = this.Erepository.findContatoByRazaoSocial(empresa.getRazaoSocial());
		Optional<EmpresaEntity> objCpfCnpj = this.Erepository.findContatoByCpfCnpj(empresa.getCpfCnpj());
		
		
		
		if(objEmail.isPresent()) {
			throw new NotFoundException("Email já Cadastrado");
		}
		
		if(objRazaoSocial.isPresent()) {
			throw new NotFoundException("Razão Social  já Cadastrado");
		}
		
		if(objCpfCnpj.isPresent()) {
			throw new NotFoundException("CPF ou CNPJ  já Cadastrado");
		}
		
		this.Erepository.save(empresa);
		
	}

	@Override
	public List<EmpresaEntity> buscartudo() {
		try {

			return this.Erepository.findAll();

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public Optional<EmpresaEntity> buscarPessoa(Long id) {
		try {

			return this.Erepository.findById(id);

		} catch (Exception e) {

			return null;

		}
	}

	@Override
	public void atualizar(Long id, EmpresaDTO empresa) throws NotFoundException {
		Optional<EmpresaEntity> objEmpresa = Erepository.findById(id);
		
		if (objEmpresa.isPresent()) {
			EmpresaEntity obj = objEmpresa.get();
			
			
				obj.setResponsavel(empresa.getResponsavel());
				obj.setEmail(empresa.getEmail());
				obj.setCpfCnpj(empresa.getCpfCnpj());
				obj.setRazaoSocial(empresa.getRazaoSocial());
				obj.setTelefone(empresa.getTelefone());
				
				Erepository.save(obj);
			}
		
		
		if(objEmpresa.isEmpty()) {
			
			throw new NotFoundException("Usuario não Cadastrado");
			
		}
		
		
		
	}

	@Override
	public void deletar(Long id) throws NotFoundException {
	
		
		Optional<EmpresaEntity> objEmpresa = Erepository.findById(id);
		
		try {
			if (objEmpresa.isEmpty()) {
				throw new NotFoundException("Usuario não cadastrado.");
			}

			Erepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Não foi possivel deletar o usuario.");
		}
	}

}
