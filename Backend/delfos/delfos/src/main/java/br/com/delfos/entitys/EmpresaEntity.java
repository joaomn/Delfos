package br.com.delfos.entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.delfos.entitys.dtos.EmpresaDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Empresa")
@NoArgsConstructor
public class EmpresaEntity implements UserDetails, Serializable{
	
	
	private static final long serialVersionUID = 1L;

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
	
	@NotBlank(message = "campo password requerido")
	private String password;
	
	
	
	public EmpresaDTO toDto() {
		return new EmpresaDTO(this);
	}
	
	public EmpresaEntity(EmpresaDTO empresa) {
		this.cpfCnpj = empresa.getCpfCnpj();
		this.email = empresa.getEmail();
		this.razaoSocial = empresa.getRazaoSocial();
		this.responsavel = empresa.getResponsavel();
		this.telefone = empresa.getTelefone();
		this.id = empresa.getId();
		this.password = empresa.getPassword();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
	
	
	

}
