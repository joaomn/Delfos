package br.com.delfos.entitys.dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.ComentarioEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
	@NotBlank
	@Column(name = "Nome", length = 180)
	private String Nome;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_Cliente")
	private Long Id;

	@Past(message = "A data deve ser no passado")
	@NotNull
	@Column(name = "Data_Nascimento")
	private LocalDate DtNascimento;

	@NotBlank
	@Column(name = "Telefone")
	private String Telefone;

	@Email
	@NotBlank
	@Column(name = "Email")
	private String email;

	private String menssagem;
	
	@NotBlank(message = "campo password requerido")
	private String password;

	public ClienteDTO(ClienteEntity Cliente) {
		this.Nome = Cliente.getNome();
		this.Id = Cliente.getId();
		this.DtNascimento = Cliente.getDtNascimento();
		this.email = Cliente.getEmail();
		this.Telefone = Cliente.getTelefone();
		this.password = Cliente.getPassword();
	}
//	
//	@OneToMany(mappedBy = "cliente")
//	private List<ComentarioEntity> comentario;

}
