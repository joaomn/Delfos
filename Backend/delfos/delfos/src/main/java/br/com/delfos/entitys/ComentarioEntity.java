package br.com.delfos.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Comentario")
public class ComentarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "campo texto requerido")
	private String texto;
	
	@Column(length = 100)
	private int avaliacao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_ID", nullable = false)
	private ClienteEntity cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_ID", nullable = false)
	private EmpresaEntity empresa;

}
