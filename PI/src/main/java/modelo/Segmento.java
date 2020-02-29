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
@Table(name = "segmento")
public class Segmento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descricao;

	public Segmento() {}

	public Long getId() { 
		return this.id; 
	} 

	public void setId(Long id){ 
		this.id = id; 
	} 

	public String getDescricao() { 
		return this.descricao; 
	} 

	public void setDescricao(String descricao){ 
		this.descricao = descricao; 
	} 
}

