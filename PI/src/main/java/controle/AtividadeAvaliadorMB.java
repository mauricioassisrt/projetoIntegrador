package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.AtividadeAvaliador;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class AtividadeAvaliadorMB {

	private String pesquisa;
	private List<AtividadeAvaliador> listaAtividadeAvaliador;
	private AtividadeAvaliador atividadeAvaliador;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public AtividadeAvaliadorMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaAtividadeAvaliador = (List<AtividadeAvaliador>) dao.listar(AtividadeAvaliador.class);
		atividadeAvaliador = new AtividadeAvaliador();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		atividadeAvaliador = new AtividadeAvaliador();
	}

	public void salvarAlterar() {
		if (atividadeAvaliador.getId() == null || atividadeAvaliador.getId() == 0) {
			dao.inserir(atividadeAvaliador);
		} else {
			dao.alterar(atividadeAvaliador);
		}
		listaAtividadeAvaliador = dao.listar(AtividadeAvaliador.class);
	}

	public void excluir(AtividadeAvaliador obj) {
		dao.excluir(obj);
		listaAtividadeAvaliador = dao.listar(AtividadeAvaliador.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<AtividadeAvaliador> getListaAtividadeAvaliador() {
		return listaAtividadeAvaliador;
	}

	public void setListaAtividadeAvaliador(List<AtividadeAvaliador> listaAtividadeAvaliador) {
		this.listaAtividadeAvaliador = listaAtividadeAvaliador;
	}

	public AtividadeAvaliador getAtividadeAvaliador() {
		return atividadeAvaliador;
	}

	public void setAtividadeAvaliador(AtividadeAvaliador atividadeAvaliador) {
		this.atividadeAvaliador = atividadeAvaliador;
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

