package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.TipoAtividade;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class TipoAtividadeMB {

	private String pesquisa;
	private List<TipoAtividade> listaTipoAtividade;
	private TipoAtividade tipoAtividade;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public TipoAtividadeMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaTipoAtividade = (List<TipoAtividade>) dao.listar(TipoAtividade.class);
		tipoAtividade = new TipoAtividade();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		tipoAtividade = new TipoAtividade();
	}

	public void salvarAlterar() {
		if (tipoAtividade.getId() == null || tipoAtividade.getId() == 0) {
			dao.inserir(tipoAtividade);
		} else {
			dao.alterar(tipoAtividade);
		}
		listaTipoAtividade = dao.listar(TipoAtividade.class);
	}

	public void excluir(TipoAtividade obj) {
		dao.excluir(obj);
		listaTipoAtividade = dao.listar(TipoAtividade.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<TipoAtividade> getListaTipoAtividade() {
		return listaTipoAtividade;
	}

	public void setListaTipoAtividade(List<TipoAtividade> listaTipoAtividade) {
		this.listaTipoAtividade = listaTipoAtividade;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
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

