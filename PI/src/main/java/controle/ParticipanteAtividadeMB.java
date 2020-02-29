package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.ParticipanteAtividade;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class ParticipanteAtividadeMB {

	private String pesquisa;
	private List<ParticipanteAtividade> listaParticipanteAtividade;
	private ParticipanteAtividade participanteAtividade;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public ParticipanteAtividadeMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaParticipanteAtividade = (List<ParticipanteAtividade>) dao.listar(ParticipanteAtividade.class);
		participanteAtividade = new ParticipanteAtividade();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		participanteAtividade = new ParticipanteAtividade();
	}

	public void salvarAlterar() {
		if (participanteAtividade.getId() == null || participanteAtividade.getId() == 0) {
			dao.inserir(participanteAtividade);
		} else {
			dao.alterar(participanteAtividade);
		}
		listaParticipanteAtividade = dao.listar(ParticipanteAtividade.class);
	}

	public void excluir(ParticipanteAtividade obj) {
		dao.excluir(obj);
		listaParticipanteAtividade = dao.listar(ParticipanteAtividade.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<ParticipanteAtividade> getListaParticipanteAtividade() {
		return listaParticipanteAtividade;
	}

	public void setListaParticipanteAtividade(List<ParticipanteAtividade> listaParticipanteAtividade) {
		this.listaParticipanteAtividade = listaParticipanteAtividade;
	}

	public ParticipanteAtividade getParticipanteAtividade() {
		return participanteAtividade;
	}

	public void setParticipanteAtividade(ParticipanteAtividade participanteAtividade) {
		this.participanteAtividade = participanteAtividade;
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

