package controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Atividade;
import modelo.Local;
import modelo.Segmento;
import modelo.TipoAtividade;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class AtividadeMB {

	private String pesquisa;
	private List<Atividade> listaAtividade;
	private Atividade atividade;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public AtividadeMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaAtividade = (List<Atividade>) dao.listar(Atividade.class);
		atividade = new Atividade();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		atividade = new Atividade();
	}

	public void salvarAlterar() {
		boolean valido = true;
		FacesContext faces = FacesContext.getCurrentInstance();
		if(atividade.getDtInicio().before(new Date())){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Data de inicio nao deve ser anterior a data atual"));	
			valido = false;
		}else if(atividade.getDtTermino().before(atividade.getDtInicio())){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Data de termino nao deve ser anterior a data de inicio"));	
			valido = false;
		}else if(atividade.getDtLimiteInscricao().after(atividade.getDtInicio())){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Data de inscricao deve ser anterior a data de inicio"));	
			valido = false;
		}else if(atividade.getDtLimiteSubmissao().after(atividade.getDtInicio())){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Data de submissao deve ser anterior a data de inicio"));	
			valido = false;
		}else if(atividade.getDtLimiteRevisao().after(atividade.getDtInicio())){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data de revisão.","Deve ser anterior a data de inicio"));	
			valido = false;
		}
		if(valido){
			if (atividade.getId() == null || atividade.getId() == 0) {
				dao.inserir(atividade);
			} else {
				dao.alterar(atividade);
			}
			listaAtividade = dao.listar(Atividade.class);
		}else{
			faces.validationFailed();
		}
	}

	public void excluir(Atividade obj) {
		dao.excluir(obj);
		listaAtividade = dao.listar(Atividade.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<Atividade> completaAtividade(String parametro){
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade = dao.listarComCondicao(Atividade.class, "LOWER(titulo)  LIKE LOWER('%"+parametro+"%')");
		return listaAtividade;
	}

	public List<Segmento> completaSegmento(String parametro){
		List<Segmento> listaSegmento = new ArrayList<Segmento>();
		listaSegmento = dao.listarComCondicao(Segmento.class, "LOWER(descricao)  LIKE LOWER('%"+parametro+"%')");
		return listaSegmento;
	}

	public List<TipoAtividade> completaTipoAtividade(String parametro){
		List<TipoAtividade> listaTipoAtividade = new ArrayList<TipoAtividade>();
		listaTipoAtividade = dao.listarComCondicao(TipoAtividade.class, "LOWER(descricao)  LIKE LOWER('%"+parametro+"%')");
		return listaTipoAtividade;
	}

	public List<Local> completaLocal(String parametro){
		List<Local> listaLocal = new ArrayList<Local>();
		listaLocal = dao.listarComCondicao(Local.class, "LOWER(descricao)  LIKE LOWER('%"+parametro+"%')");
		return listaLocal;
	}

	public List<Atividade> getListaAtividade() {
		return listaAtividade;
	}

	public void setListaAtividade(List<Atividade> listaAtividade) {
		this.listaAtividade = listaAtividade;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public boolean isFrmLista() {
		return frmLista;
	}

	public void setFrmLista(boolean frmLista) {
		this.frmLista = frmLista;
	}

	public boolean isFrmCadastro() {
		return frmCadastro;
	}

	public void setFrmCadastro(boolean frmCadastro) {
		this.frmCadastro = frmCadastro;
	}
}

