package modelo;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "submissao")
public class Submissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tituloTrabalho;
	private String status;
	@ManyToOne
	private Atividade atividade;
	@ManyToOne
	private Submissao submissao;
	@ManyToOne
	private Pessoa pessoa;

	public Submissao() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public String gettituloTrabalho() { 
		return this.tituloTrabalho; 
	} 

	public void settituloTrabalho(String tituloTrabalho){ 
		this.tituloTrabalho = tituloTrabalho; 
	} 

	public String getStatus() { 
		return this.status; 
	} 

	public void setStatus(String status){ 
		this.status = status; 
	} 

	public Atividade getAtividade() { 
		return this.atividade; 
	} 

	public void setAtividade(Atividade atividade){ 
		this.atividade = atividade; 
	} 

	public Submissao getSubmissao() { 
		return this.submissao; 
	} 

	public void setSubmissao(Submissao submissao){ 
		this.submissao = submissao; 
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	} 
	
}

