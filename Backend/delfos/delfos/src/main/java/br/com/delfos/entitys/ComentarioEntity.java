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

import br.com.delfos.entitys.dtos.ComentarioDTO;
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
	
	@ManyToOne()
	@JoinColumn(name = "cliente_ID", nullable = true)
	private ClienteEntity cliente;
	
	@OneToOne()
	@JoinColumn(name = "anuncio_ID", nullable = true)
	private AnuncioEntity anuncio;
	
	public ComentarioDTO toDto() {
		return new ComentarioDTO(this);
	}
	
	
	public ComentarioEntity(ComentarioDTO comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.avaliacao = comentario.getAvaliacao();
		this.cliente = comentario.getCliente();
		this.anuncio = comentario.getAnuncio();
		
	}

}
