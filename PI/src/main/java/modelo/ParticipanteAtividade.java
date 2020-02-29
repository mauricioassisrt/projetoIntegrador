package modelo;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
@Table(name="pessoaatividade",uniqueConstraints = {@UniqueConstraint( columnNames={"pessoa_id", "atividade_id"})})

public class ParticipanteAtividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	//@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	@ManyToOne

	//@JoinColumn(name="atividade_id")
	private Atividade atividade;

	private boolean frequencia;

	public ParticipanteAtividade() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public Pessoa getPessoa() { 
		return this.pessoa; 
	} 

	public void setPessoa(Pessoa pessoa){ 
		this.pessoa = pessoa; 
	} 

	public Atividade getAtividade() { 
		return this.atividade; 
	} 

	public void setAtividade(Atividade atividade){ 
		this.atividade = atividade; 
	} 

	public boolean getFrequencia() { 
		return this.frequencia; 
	} 

	public void setFrequencia(boolean frequencia){ 
		this.frequencia = frequencia; 
	} 
}

