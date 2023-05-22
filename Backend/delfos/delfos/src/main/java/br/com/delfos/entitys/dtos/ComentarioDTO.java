package br.com.delfos.entitys.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.ClienteEntity;
import br.com.delfos.entitys.ComentarioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "campo texto requerido")
	private String texto;
	
	@Column(length = 100)
	private int avaliacao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_ID", nullable = true)
	private ClienteEntity cliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "anuncio_ID", nullable = true)
	private AnuncioEntity anuncio;
	
	private String menssagem;

	public ComentarioDTO(ComentarioEntity comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.avaliacao = comentario.getAvaliacao();
		this.cliente = comentario.getCliente();
		this.anuncio = comentario.getAnuncio();
		
	}
}
