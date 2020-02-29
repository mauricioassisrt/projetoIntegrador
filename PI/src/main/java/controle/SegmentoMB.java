package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Segmento;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class SegmentoMB {

	private String pesquisa;
	private List<Segmento> listaSegmento;
	private Segmento segmento;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public SegmentoMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listaSegmento = (List<Segmento>) dao.listar(Segmento.class);
		segmento = new Segmento();
		frmLista = true;
		frmCadastro = false;
	}

	public void novoRegistro() {
		segmento = new Segmento();
	}

	public void salvarAlterar() {
		if (segmento.getId() == null || segmento.getId() == 0) {
			dao.inserir(segmento);
		} else {
			dao.alterar(segmento);
		}
		listaSegmento = dao.listar(Segmento.class);
	}

	public void excluir(Segmento obj) {
		dao.excluir(obj);
		listaSegmento = dao.listar(Segmento.class);
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<Segmento> getListaSegmento() {
		return listaSegmento;
	}

	public void setListaSegmento(List<Segmento> listaSegmento) {
		this.listaSegmento = listaSegmento;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
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

