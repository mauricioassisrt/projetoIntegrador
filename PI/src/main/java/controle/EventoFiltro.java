package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Atividade;
import modelo.ParticipanteAtividade;
import modelo.Pessoa;
import util.DAOGenerico;
import util.UsuarioLogado;

@ManagedBean
@SessionScoped
public class EventoFiltro {
	private DAOGenerico dao;
	private List<Atividade> listaCondicao;
	private Atividade atividade;
	private List<Atividade> listaAtividade;
	private ParticipanteAtividade objetoParticipanteAtividade = new ParticipanteAtividade();
	
	private AtividadeMB objetMb;
	private Pessoa pess= new Pessoa();
	public EventoFiltro() {

		dao = new DAOGenerico();
		listaCondicao = (List<Atividade>) dao.listarComCondicao(Atividade.class, "id_atividade is not null");
		atividade = new Atividade();
	}

	public void pegaPessoa(){
		pess = UsuarioLogado.retornaUsuarioLogado();
	}

	public void alterarUsuario() throws IOException{
		
	if(pess.getId()!=null){
		dao.alterar(pess);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
	}else{
		System.out.println("Erro");
	}
	}
	public void alterarAdmin() throws IOException{
		FacesContext faces = FacesContext.getCurrentInstance();
		
		if(pess.getId()!=null){
			dao.alterar(pess);
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Alterado com sucesso!"));	
		}else{
			System.out.println("Erro");
		}
		}
	
	public void inserirPessoaAtividade() throws IOException{
		pess = UsuarioLogado.retornaUsuarioLogado();
		
		FacesContext faces = FacesContext.getCurrentInstance();

				if(atividade.getVagas()>dao.listarComCondicao(ParticipanteAtividade.class, "atividade_id ="+atividade.getId() ).size()){
					objetoParticipanteAtividade.setFrequencia(false);
					objetoParticipanteAtividade.setAtividade(atividade);
					objetoParticipanteAtividade.setPessoa(pess);
					dao.inserir(objetoParticipanteAtividade);
					objetoParticipanteAtividade = new ParticipanteAtividade();
					faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Cadastrado com sucesso!"));	
					FacesContext.getCurrentInstance().getExternalContext().redirect("./index.jsf");
				}else{
					faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","As vagas estão todas preenchidas para o evento!"));
				}	
			
	}

	public List<Atividade> getListaAtividade() {
		return listaAtividade;
	}

	public void setListaAtividade(List<Atividade> listaAtividade) {
		this.listaAtividade = listaAtividade;
	}

	public ParticipanteAtividade getObjetoParticipanteAtividade() {
		return objetoParticipanteAtividade;
	}
	public void setObjetoParticipanteAtividade(ParticipanteAtividade objetoParticipanteAtividade) {
		this.objetoParticipanteAtividade = objetoParticipanteAtividade;
	}
	public Pessoa getPess() {
		return pess;
	}
	public void setPess(Pessoa pess) {
		this.pess = pess;
	}
	public List<Atividade> getListaCondicao() {
		return listaCondicao;
	}

	public void setListaCondicao(List<Atividade> listaCondicao) {
		this.listaCondicao = listaCondicao;
	}

	public DAOGenerico getDao() {
		return dao;
	}

	public void setDao(DAOGenerico dao) {
		this.dao = dao;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
}
