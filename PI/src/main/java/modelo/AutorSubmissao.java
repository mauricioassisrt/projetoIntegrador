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
@Table(name = "autorSubmissao")
public class AutorSubmissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	@ManyToOne
	private Submissao submissao;

	public AutorSubmissao() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Submissao getSubmissao() { 
		return this.submissao; 
	} 

	public void setSubmissao(Submissao submissao){ 
		this.submissao = submissao; 
	} 
}

