package modelo;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity

@Table(name="atividadeAvaliador",uniqueConstraints = {@UniqueConstraint( columnNames={"pessoa_id", "atividade_id"})})
public class AtividadeAvaliador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Atividade atividade;
	@ManyToOne
	private Pessoa pessoa;

	public AtividadeAvaliador() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public Atividade getAtividade() { 
		return this.atividade; 
	} 

	public void setAtividade(Atividade atividade){ 
		this.atividade = atividade; 
	} 

	public Pessoa getPessoa() { 
		return this.pessoa; 
	} 

	public void setPessoa(Pessoa pessoa){ 
		this.pessoa = pessoa; 
	} 
}

