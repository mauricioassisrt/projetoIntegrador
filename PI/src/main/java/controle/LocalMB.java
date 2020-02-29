package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Local;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class LocalMB {

	private String pesquisa;
	private List<Local> listaLocal;
	private Local local;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public LocalMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaLocal = (List<Local>) dao.listar(Local.class);
		local = new Local();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		local = new Local();
	}

	public void salvarAlterar() {
		if (local.getId() == null || local.getId() == 0) {
			dao.inserir(local);
		} else {
			dao.alterar(local);
		}
		listaLocal = dao.listar(Local.class);
	}

	public void excluir(Local obj) {
		dao.excluir(obj);
		listaLocal = dao.listar(Local.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<Local> getListaLocal() {
		return listaLocal;
	}

	public void setListaLocal(List<Local> listaLocal) {
		this.listaLocal = listaLocal;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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

