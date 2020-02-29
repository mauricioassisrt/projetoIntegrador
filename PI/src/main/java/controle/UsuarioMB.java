package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Pessoa;
import util.DAOGenerico;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	private String pesquisa;
	private List<Pessoa> listaPessoa;
	private Pessoa pessoa;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;

	public UsuarioMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
		listar();
		pessoa = new Pessoa();
		frmLista = true;
		frmCadastro = false;
	}
	private void listar(){
		listaPessoa = (List<Pessoa>) dao.listarComCondicao(Pessoa.class, "permissao = 'administrador'");
	}

	public void novoRegistro() {
		pessoa = new Pessoa();
	}

	public void salvarAlterar() {
		if(pessoa.getEmail() != null && dao.listarComCondicao(Pessoa.class, "LOWER(email)  LIKE LOWER('%"+pessoa.getEmail()+"%')").size() <= 0){
			if (pessoa.getId() == null || pessoa.getId() == 0) {
				dao.inserir(pessoa);
			} else {
				dao.alterar(pessoa);
			}
			listar();
		}
	}

	public void excluir(Pessoa obj) {
		dao.excluir(obj);
		listar();
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

