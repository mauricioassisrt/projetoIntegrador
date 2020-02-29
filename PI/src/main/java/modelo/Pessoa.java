package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cpf;
	private Date dtNasc;
	private String email;
	private String telefone;
	private String senha;
	private String permissao;
	@ManyToOne
	@JoinColumn(name = "id_segmento")
	private Segmento segmento;

	public Pessoa() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public String getNome() { 
		return this.nome; 
	} 

	public void setNome(String nome){ 
		this.nome = nome; 
	} 

	public String getCpf() { 
		return this.cpf; 
	} 

	public void setCpf(String cpf){ 
		this.cpf = cpf; 
	} 

	public Date getDtNasc() { 
		return this.dtNasc; 
	} 

	public void setDtNasc(Date dtNasc){ 
		this.dtNasc = dtNasc; 
	} 

	public String getEmail() { 
		return this.email; 
	} 

	public void setEmail(String email){ 
		this.email = email; 
	} 

	public String getTelefone() { 
		return this.telefone; 
	} 

	public void setTelefone(String telefone){ 
		this.telefone = telefone; 
	} 

	public String getSenha() { 
		return this.senha; 
	} 

	public void setSenha(String senha){ 
		this.senha = senha; 
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	} 
}

