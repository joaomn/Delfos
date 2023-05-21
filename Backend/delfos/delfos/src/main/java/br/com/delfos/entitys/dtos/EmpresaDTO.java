package br.com.delfos.entitys.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.delfos.entitys.EmpresaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "campo razao social requerido")
	@Column(name = "Razao_Social")
	private String razaoSocial;
	
	
	@NotBlank(message = "campo telefone requerido")
	private String telefone;
	
	@Email
	@NotBlank(message = "O campo email é obrigatorio")
	private String email;
	
	@NotBlank(message = "campo nome de responssavel requerido")
	@Column(name = "Nome_Responssavel")
	private String responsavel;
	
	@NotBlank(message = "campo cpf/cnpj requerido")
	@Column(name = "CNPJ_CPF")
	private String cpfCnpj;
	
	private String menssagem;
	
	public EmpresaDTO(EmpresaEntity empresa) {
		this.cpfCnpj = empresa.getCpfCnpj();
		this.email = empresa.getEmail();
		this.razaoSocial = empresa.getRazaoSocial();
		this.responsavel = empresa.getResponsavel();
		this.telefone = empresa.getTelefone();
		this.id = empresa.getId();
	}

}
