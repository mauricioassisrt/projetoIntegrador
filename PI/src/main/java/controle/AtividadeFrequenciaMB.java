package controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Atividade;
import modelo.ParticipanteAtividade;
import modelo.Pessoa;
import util.ChamaRelatorio;
import util.DAOGenerico;
import util.UsuarioLogado;

@ManagedBean
@SessionScoped
public class AtividadeFrequenciaMB {

	private Atividade atividadeSelecionada;
	private List<ParticipanteAtividade> listaParticipante;
	private DAOGenerico dao;
	private Pessoa usuarioLogado;
	private boolean atividadeValida;
	private String nomeParticipanteFiltro;

	public AtividadeFrequenciaMB() {
		atividadeValida = false;
		dao = new DAOGenerico();
		usuarioLogado = UsuarioLogado.retornaUsuarioLogado();
	}

	public void gerarListaFrequencia() {
		atividadeValida = false;
		if(atividadeSelecionada.getDtInicio().before(new Date())){
			listaParticipante = dao.listarComCondicao(ParticipanteAtividade.class, "atividade.id =" + atividadeSelecionada.getId());
			if(listaParticipante.size() == 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"INFO","Evento selecionado nao possui participantes"));
			}else{
				atividadeValida = true;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"AVISO","Evento selecionado ainda nao ocorreu"));
		}
	}
	public void filtrarListaParticipante(){
		listaParticipante = new ArrayList<ParticipanteAtividade>();
		listaParticipante = dao.listarComCondicao(ParticipanteAtividade.class, "LOWER(pessoa.nome) LIKE LOWER('%" + nomeParticipanteFiltro+"%') AND atividade.id ="+atividadeSelecionada.getId());
	}

	public void imprimeListaParticipante(){
		HashMap parameters = new HashMap<String, String>();
		parameters.put("POR_ATIVIDADE", atividadeSelecionada.getId());
		ChamaRelatorio.imprimeRelatorio("ListaParticipantes.jasper", parameters, "ListaDeParticipantes");
	}
	
	public void alterarFrequencia(ParticipanteAtividade obj) {
		obj.setFrequencia(!obj.getFrequencia());
		dao.alterar(obj);
		gerarListaFrequencia();
	}

	public List<Atividade> completaAtividade(String parametro) {
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade = dao.listarComCondicao(Atividade.class, "LOWER(titulo)  LIKE LOWER('%" + parametro + "%')");
		return listaAtividade;
	}

	public Atividade getAtividadeSelecionada() {
		return atividadeSelecionada;
	}

	public void setAtividadeSelecionada(Atividade atividadeSelecionada) {
		this.atividadeSelecionada = atividadeSelecionada;
	}

	public List<ParticipanteAtividade> getListaParticipante() {
		return listaParticipante;
	}

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public boolean isAtividadeValida() {
		return atividadeValida;
	}

	public void setNomeParticipanteFiltro(String nomeParticipanteFiltro) {
		this.nomeParticipanteFiltro = nomeParticipanteFiltro;
	}

	public String getNomeParticipanteFiltro() {
		return nomeParticipanteFiltro;
	}
}
