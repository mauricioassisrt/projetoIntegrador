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
import javax.persistence.Temporal;

@Entity
@Table(name = "atividade")
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_atividade")
	private Atividade atividade;
	private String titulo;
	private String informacao;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dtInicio;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dtTermino;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dtLimiteInscricao;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dtLimiteSubmissao;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date dtLimiteRevisao;
	private String cargaHoraria;
	@ManyToOne
	@JoinColumn(name = "id_segmento")
	private Segmento segmento;
	@ManyToOne
	@JoinColumn(name = "id_tipo_atividade")
	private TipoAtividade tipoAtividade;
	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;
	private int vagas;
	
	

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public Atividade() {}

	public Long getId() { 
		return this.id; 
	} 

	public Date getDtLimiteInscricao() {
		return dtLimiteInscricao;
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

	public String getTitulo() { 
		return this.titulo; 
	} 

	public void setTitulo(String titulo){ 
		this.titulo = titulo; 
	} 

	public String getInformacao() { 
		return this.informacao; 
	} 

	public void setInformacao(String informacao){ 
		this.informacao = informacao; 
	} 

	public Date getDtInicio() { 
		return this.dtInicio; 
	} 

	public void setDtInicio(Date dtInicio){ 
		this.dtInicio = dtInicio; 
	} 

	public Date getDtTermino() { 
		return this.dtTermino; 
	} 

	public void setDtTermino(Date dtTermino){ 
		this.dtTermino = dtTermino; 
	} 

	public Date eventoFiltro() { 
		return this.dtLimiteInscricao; 
	} 

	public void setDtLimiteInscricao(Date dtLimiteInscricao){ 
		this.dtLimiteInscricao = dtLimiteInscricao; 
	} 

	public Date getDtLimiteSubmissao() {
		return dtLimiteSubmissao;
	}

	public Date getDtLimiteRevisao() {
		return dtLimiteRevisao;
	}

	public void setDtLimiteSubmissao(Date dtLimiteSubmissao) {
		this.dtLimiteSubmissao = dtLimiteSubmissao;
	}

	public void setDtLimiteRevisao(Date dtLimiteRevisao) {
		this.dtLimiteRevisao = dtLimiteRevisao;
	}

	public String getCargaHoraria() { 
		return this.cargaHoraria; 
	} 

	public void setCargaHoraria(String cargaHoraria){ 
		this.cargaHoraria = cargaHoraria; 
	} 

	public Segmento getSegmento() { 
		return this.segmento; 
	} 

	public void setSegmento(Segmento segmento){ 
		this.segmento = segmento; 
	} 

	public TipoAtividade getTipoAtividade() { 
		return this.tipoAtividade; 
	} 

	public void setTipoAtividade(TipoAtividade tipoAtividade){ 
		this.tipoAtividade = tipoAtividade; 
	} 

	public Local getLocal() { 
		return this.local; 
	} 

	public void setLocal(Local local){ 
		this.local = local; 
	} 
}

