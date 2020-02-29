package controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Atividade;
import modelo.ParticipanteAtividade;
import modelo.Pessoa;
import util.ChamaRelatorio;
import util.DAOGenerico;
import util.UsuarioLogado;

@ManagedBean
@ViewScoped
public class IndexParticipanteMB {
	private List<Atividade> listaAtividade;
	private List<Atividade> listaAtividadeCertificado;
	private Pessoa participanteLogado;
	private DAOGenerico dao;

	public IndexParticipanteMB() {
		listaAtividadeCertificado = new ArrayList<>();
		participanteLogado = UsuarioLogado.retornaUsuarioLogado();
		dao = new DAOGenerico();
		listar();

	}
	private void listar(){
		listaAtividade = new ArrayList<Atividade>();
		for(ParticipanteAtividade p :(List<ParticipanteAtividade>) dao.listarComCondicao(ParticipanteAtividade.class, " pessoa.id = "+participanteLogado.getId())){
			listaAtividade.add(p.getAtividade());
			if(p.getFrequencia()){
				listaAtividadeCertificado.add(p.getAtividade());
			}
		}

	}

	public void geraCertificadoAtividade(Atividade atividade){
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM");

		HashMap parameters = new HashMap<String, String>();
		parameters.put("participanteNome", participanteLogado.getNome());
		parameters.put("participanteCPF", participanteLogado.getCpf());
		parameters.put("atividadeNome", atividade.getTitulo());
		parameters.put("localNome", atividade.getLocal().getDescricao());
		parameters.put("dataInicio", dt.format(atividade.getDtInicio()));
		parameters.put("dataTermino", dt.format(atividade.getDtTermino()));
		parameters.put("cargaHoraria", atividade.getCargaHoraria());
		parameters.put("data", (Date) atividade.getDtTermino());
		parameters.put("numeroValidacao", new Random().nextInt()+"");
		ChamaRelatorio.imprimeRelatorio("Certificado.jasper", parameters, "certificado");
	}

	public List<Atividade> getListaAtividade() {
		return listaAtividade;
	}
	public Pessoa getParticipanteLogado() {
		return participanteLogado;
	}
	public void setListaAtividade(List<Atividade> listaAtividade) {
		this.listaAtividade = listaAtividade;
	}
	public void setParticipanteLogado(Pessoa participanteLogado) {
		this.participanteLogado = participanteLogado;
	}
	public List<Atividade> getListaAtividadeCertificado() {
		return listaAtividadeCertificado;
	}
	public void setListaAtividadeCertificado(List<Atividade> listaAtividadeCertificado) {
		this.listaAtividadeCertificado = listaAtividadeCertificado;
	}
}
