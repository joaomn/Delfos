package br.com.delfos.entitys;





import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.delfos.entitys.dtos.ClienteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Clientes")
@NoArgsConstructor
public class ClienteEntity implements UserDetails, Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo nome requerido")
	@Column(name = "Nome", length = 180)
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_Cliente")
	private Long id;
	
	@Past(message = "A data deve ser no passado")
	@NotNull(message = "Campo data requerido")
	@Column(name = "Data_Nascimento")	
	private LocalDate dtNascimento;
	
	@NotBlank(message = "Campo telefone requerido")
	@Column(name = "Telefone")
	private String telefone;
	
	@Email
	@NotBlank(message = "Campo email requerido")
	@Column(name = "Email")
	private String email;
	
	@NotBlank(message = "campo password requerido")
	private String password;
	
	
	public ClienteDTO toDto() {
		return new ClienteDTO(this);
	}
	
	
	public ClienteEntity(ClienteDTO dto) {
		this.telefone = dto.getTelefone();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.dtNascimento = dto.getDtNascimento();
		this.password = dto.getPassword();
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
