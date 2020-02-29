package controle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import modelo.AtividadeAvaliador;
import modelo.AutorSubmissao;
import modelo.AvaliadorSubmissao;
import modelo.Pessoa;
import modelo.Submissao;
import util.DAOGenerico;
import util.EnviaEmail;
import util.ParametrosGlobais;
import util.Status;
import util.UsuarioLogado;

@ManagedBean
@SessionScoped
public class SubmissaoMB implements Serializable{

	private String pesquisa;
	private List<Submissao> listaSubmissao;
	private Submissao submissao;
	private DAOGenerico dao;
	boolean frmLista;
	boolean frmCadastro;
	boolean frmCadastroAutor;
	private Pessoa pessoa = new Pessoa();
	private List<AutorSubmissao> listaAutor;
	private AutorSubmissao autorN;
	private List<AtividadeAvaliador> listaAtividadeAvaliador;
	EnviaEmail email = new EnviaEmail();
	
	//upload
    private UploadedFile resume;
    private String filePath=ParametrosGlobais.diretorioSubmissao;
    private String fileName;
    //fim upload
    
    
	public SubmissaoMB() {
		pesquisa = ""; 
		dao = new DAOGenerico();
//		listaSubmissao = (List<Submissao>) dao.listar(Submissao.class);
		pessoa = UsuarioLogado.retornaUsuarioLogado();
		atualizaListaSubmissao();
		listaAutor = new ArrayList<AutorSubmissao>();
		iniciaMB();		
	}

	public void novoRegistro() {
		submissao = new Submissao();
	}
	
	public void atualizaListaSubmissao(){
		listaSubmissao = (List<Submissao>) dao.listarComCondicao(Submissao.class, "pessoa_id =" + pessoa.getId());
	}

	public void iniciaMB(){
		submissao = new Submissao();
		submissao.setPessoa(pessoa);
		frmLista = false;
		frmCadastro = true;
		autorN = new AutorSubmissao();
		resume = null;
	}
	
	public void salvarAlterar() throws IOException, EmailException {
		if (submissao.getId() == null || submissao.getId() == 0) {
			agregaAutor();
			submissao.setStatus(Status.AGUARDANDO);
			dao.inserir(submissao);
			fileName = Long.toString(submissao.getId()).trim()+".pdf";
			this.uploadResume();
			salvarAutor();
			salvarAvaliador();
			
		} else {
			dao.alterar(submissao);
		}
		atualizaListaSubmissao();
//		return "submissaoCadastroAutor.xhtml";
	}

	public void excluir(Submissao obj) {
		dao.excluir(obj);
		atualizaListaSubmissao();
	}

	public void pesquisar() {
		if (pesquisa.trim().length() > 0) {

		}
	}

	// AVALIADOR
	
	/**
	 * Busca Avaliador da Atividade Cadastrada 
	 */
	public void pegaAvaliador(){
		listaAtividadeAvaliador = (List<AtividadeAvaliador>) dao.listarComCondicao(AtividadeAvaliador.class, "atividade_id =" + submissao.getAtividade().getId());		
	}
	
	/**
	 * Encaminha Trabalho para os Avaliadores 
	 * @throws EmailException 
	 */
	public void salvarAvaliador() throws EmailException{
		pegaAvaliador();
		AvaliadorSubmissao avaliador;
		
		for(AtividadeAvaliador avaliadorS:listaAtividadeAvaliador){
			avaliador = new AvaliadorSubmissao();
			avaliador.setSubmissao(submissao);
			avaliador.setParecer(Status.AGUARDANDO);
			avaliador.setAvaliador(avaliadorS.getPessoa());
			dao.inserir(avaliador);
			email.sendEmail(avaliador.getAvaliador().getEmail().trim()
					, avaliador.getAvaliador().getNome().trim()
					, "Autor: "+pessoa.getNome().trim()+"\nTitulo: "+submissao.gettituloTrabalho().trim()
					, "Existe uma nova Submissão a ser Avaliada!!!");
		}
	}
	
	
	// FIM AVALIADOR
	
	// AUTOR
	
	/**
	 * Adiciona Autor Logado 
	 */
	public void agregaAutor(){
		AutorSubmissao autor = new AutorSubmissao();
		autor.setNome(pessoa.getNome());
		autor.setEmail(pessoa.getEmail());
		listaAutor.add(autor);
	}
	
	/**
	 * Adiciona Autor na Lista 
	 */
	public void agregaAutorNovo(){
		listaAutor.add(autorN);
		autorN = new AutorSubmissao();
	}
	
	/**
	 * Exclui Autor da Lista 
	 */
	public void excluiAutor(AutorSubmissao autorR){
		if(listaAutor.size()>1){
			listaAutor.remove(autorR);
		}
	}
	
	/**
	 * Salva Autor no Banco 
	 */
	public void salvarAutor(){
		for(AutorSubmissao autorL:listaAutor){
			autorL.setSubmissao(submissao);
			dao.inserir(autorL);
		}
		listaAutor = new ArrayList<AutorSubmissao>();
	}
	
	public void chamaSalvarAutor(){
		salvarAutor();
		iniciaMB();
	}
	// FIM AUTOR
	
	//upload
	
	/**
	 * Metodo utilizado para atribuir o arquivo upado para a variavel
	 */
	public void pegaArquivo(FileUploadEvent e) throws IOException{
		 
        e.getFile().getContents();
        this.resume=e.getFile();
        
	}
	
	/**
	 * Metodo utilizado para salvar o arquivo local escolhido
	 */
	public String uploadResume() throws IOException {

		if (null != resume) {
			byte[] bytes = resume.getContents();
//			String filename = FilenameUtils.getName(resume.getFileName());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
			stream.write(bytes);
			stream.close();
		}
		return "success";
	}
		
	//fim upload
	
	public List<Submissao> getListaSubmissao() {
		return listaSubmissao;
	}

	public void setListaSubmissao(List<Submissao> listaSubmissao) {
		this.listaSubmissao = listaSubmissao;
	}

	public Submissao getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Submissao submissao) {
		this.submissao = submissao;
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
	
	public boolean isFrmCadastroAutor() {
		return frmCadastroAutor;
	}

	public void setFrmCadastroAutor(boolean frmCadastroAutor) {
		this.frmCadastroAutor = frmCadastroAutor;
	}

	public UploadedFile getResume() {
        return resume;
    }
 
    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<AutorSubmissao> getListaAutor() {
		return listaAutor;
	}

	public void setListaAutor(List<AutorSubmissao> listaAutor) {
		this.listaAutor = listaAutor;
	}

	public AutorSubmissao getAutorN() {
		return autorN;
	}

	public void setAutorN(AutorSubmissao autorN) {
		this.autorN = autorN;
	}

	public List<AtividadeAvaliador> getListaAtividadeAvaliador() {
		return listaAtividadeAvaliador;
	}

	public void setListaAtividadeAvaliador(List<AtividadeAvaliador> listaAtividadeAvaliador) {
		this.listaAtividadeAvaliador = listaAtividadeAvaliador;
	}

	
    
}

