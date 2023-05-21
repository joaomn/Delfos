package br.com.delfos.entitys.dtos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.delfos.entitys.AnuncioEntity;
import br.com.delfos.entitys.EmpresaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnuncioDTO {
	
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
	
		
	
	
	@NotBlank(message = "texto do anuncio não informado")
	@Column(length = 600)
	private String texto;
	
	@NotBlank(message = "coluna tipo requerida")
	private String tipo;	
	
	
	@NotBlank(message = "Valor do anuncio não informado")
	private String valor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String messagem;
	
	@ManyToOne
	@JoinColumn(name = "EMPRESA_ID")
	private EmpresaEntity empresa;

	public AnuncioDTO(AnuncioEntity anuncio) {
		
		this.titulo = anuncio.getTitulo();
		this.dtPostagem = anuncio.getDtPostagem();
		this.email = anuncio.getEmail();
		this.telefone = anuncio.getTelefone();
		this.avaliacao = anuncio.getAvaliacao();		
		this.texto = anuncio.getTexto();
		this.valor = anuncio.getValor();
		this.id = anuncio.getId();
		this.empresa = anuncio.getEmpresa();
		this.tipo = anuncio.getTipo();
		
		
	}
	
	 
	
	
	

}
