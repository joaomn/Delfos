package br.com.delfos.entitys;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.delfos.entitys.dtos.AnuncioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Anuncio")
@NoArgsConstructor
public class AnuncioEntity {
	
	@NotBlank(message = "titulo do anuncio não informado")
	@Column(name = "nome_anuncio")
	private String titulo;
	
	@NotNull(message = "data do anuncio não informada")
	@Column(name = " Data_postagem")
	private LocalDate dtPostagem;
	
	
	@Email
	private String email;
	
	@NotBlank(message = "titulo do anuncio não informado")
	private String telefone;
	
	@Column(length = 100)
	private int avaliacao;
	
		
	private boolean logo;
	
	@NotBlank(message = "texto do anuncio não informado")
	@Column(length = 600)
	private String texto;
	
	@NotBlank(message = "Valor do anuncio não informado")
	private String valor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID", nullable = false)
	private EmpresaEntity empresa;
	
	
	public AnuncioDTO toDto() {
		return new AnuncioDTO(this);
	}
	
public AnuncioEntity(AnuncioDTO anuncio) {
		
		this.titulo = anuncio.getTitulo();
		this.dtPostagem = anuncio.getDtPostagem();
		this.email = anuncio.getEmail();
		this.telefone = anuncio.getTelefone();
		this.avaliacao = anuncio.getAvaliacao();		
		this.texto = anuncio.getTexto();
		this.valor = anuncio.getValor();
		this.id = anuncio.getId();
		
		
	}

}
