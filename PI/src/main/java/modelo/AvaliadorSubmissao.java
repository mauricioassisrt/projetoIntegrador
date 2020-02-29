package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliadorSubmissao")
public class AvaliadorSubmissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_avaliador")
	private Pessoa avaliador;
	@ManyToOne
	@JoinColumn(name = "id_submissao")
	private Submissao submissao;
	private String comentario;
	private String parecer;

	public AvaliadorSubmissao() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public Pessoa getAvaliador() { 
		return this.avaliador; 
	} 

	public void setAvaliador(Pessoa pessoa){ 
		this.avaliador = pessoa; 
	} 

	public Submissao getSubmissao() { 
		return this.submissao; 
	} 

	public void setSubmissao(Submissao submissao){ 
		this.submissao = submissao; 
	} 

	public String getComentario() { 
		return this.comentario; 
	} 

	public void setComentario(String comentario){ 
		this.comentario = comentario; 
	} 

	public String getParecer() { 
		return this.parecer; 
	} 

	public void setParecer(String parecer){ 
		this.parecer = parecer; 
	} 
}

