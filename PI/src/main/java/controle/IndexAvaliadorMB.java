package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import modelo.AvaliadorSubmissao;
import modelo.Pessoa;
import modelo.Submissao;
import util.DAOGenerico;
import util.EnviaEmail;
import util.ParametrosGlobais;
import util.Status;
import util.UsuarioLogado;

@ManagedBean
@ViewScoped
public class IndexAvaliadorMB {

	private Pessoa avaliador;
	private DAOGenerico dao;
	private List<AvaliadorSubmissao> listaSubmissoes;
	private AvaliadorSubmissao submissaoSelecionada;
	private StreamedContent artigo;
	private boolean statusAvaliacao;
	EnviaEmail email = new EnviaEmail();

	public IndexAvaliadorMB() {
		statusAvaliacao = false;
		dao = new DAOGenerico();
		avaliador = UsuarioLogado.retornaUsuarioLogado();
		listar();	
	}

	public StreamedContent getArtigo() {
		File file = new File(ParametrosGlobais.diretorioSubmissao+submissaoSelecionada.getSubmissao().getId()+".pdf");
		InputStream stream;
		try {
			stream = new FileInputStream(file);
			artigo = new DefaultStreamedContent(stream, "application/pdf", "artigo.pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		statusAvaliacao = true;
		RequestContext.getCurrentInstance().update("frmDialog");
		return artigo;
	}

	public void alterarStatusSubmissao(){
		List<AvaliadorSubmissao> listaSub = dao.listarComCondicao(AvaliadorSubmissao.class, "id_submissao="+submissaoSelecionada.getSubmissao().getId());
		Submissao submissao = submissaoSelecionada.getSubmissao();
		Boolean statusAvaliacao = false; //todos não avaliaram
		Boolean aprovado = true;
		for(AvaliadorSubmissao sub: listaSub){
			if(sub.getParecer().equals(Status.AGUARDANDO)){
				submissao.setStatus(Status.AVALIANDO);
				break;
			}else if(sub.getParecer().equals(Status.REPROVADO)){
				aprovado = false;
			}
		}
		if(aprovado && !submissao.getStatus().equals(Status.AVALIANDO)){
			submissao.setStatus(Status.APROVADO);
		}else if(!aprovado && !submissao.getStatus().equals(Status.AVALIANDO)){
			submissao.setStatus(Status.REPROVADO);
		}
		dao.alterar(submissao);
	}
	
	public void avaliarSubmissao() throws EmailException{
		if(statusAvaliacao){
			dao.alterar(submissaoSelecionada);
			alterarStatusSubmissao();
			email.sendEmail(submissaoSelecionada.getSubmissao().getPessoa().getEmail().trim()
			, submissaoSelecionada.getSubmissao().getPessoa().getNome().trim()
			, "Avaliação do Trabalho: " + submissaoSelecionada.getSubmissao().gettituloTrabalho().trim() + " foi realizado!\n"
				+ "Observação: "+submissaoSelecionada.getComentario().trim()+"\n"
				+ "Parecer: "+submissaoSelecionada.getParecer().trim()
			, "Sua Submissão foi Avaliada!!!");
		}else{
			FacesContext faces = FacesContext.getCurrentInstance();
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Voce deve baixar primeiro o artigo antes de avaliar"));
			faces.validationFailed();
		}
	}
	
	public void listar(){
		listaSubmissoes = (List<AvaliadorSubmissao>) dao.listarComCondicao(AvaliadorSubmissao.class, " avaliador.id = "+avaliador.getId());
	}

	public boolean isStatusAvaliacao() {
		return statusAvaliacao;
	}

	public void setStatusAvaliacao(boolean statusAvaliacao) {
		this.statusAvaliacao = statusAvaliacao;
	}

	public Pessoa getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Pessoa avaliador) {
		this.avaliador = avaliador;
	}

	public List<AvaliadorSubmissao> getListaSubmissoes() {
		return listaSubmissoes;
	}

	public void setListaSubmissoes(List<AvaliadorSubmissao> listaSubmissoes) {
		this.listaSubmissoes = listaSubmissoes;
	}

	public AvaliadorSubmissao getSubmissaoSelecionada() {
		return submissaoSelecionada;
	}

	public void setSubmissaoSelecionada(AvaliadorSubmissao submissaoSelecionada) {
		statusAvaliacao = false;
		this.submissaoSelecionada = submissaoSelecionada;
	}
}
