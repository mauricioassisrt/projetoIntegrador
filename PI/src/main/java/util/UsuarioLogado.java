package util;

import org.springframework.security.core.context.SecurityContextHolder;

import modelo.Pessoa;

public class UsuarioLogado {

	public static Pessoa retornaUsuarioLogado(){
		try {
			Pessoa p = new Pessoa();
			p = (Pessoa) new DAOGenerico().listarComCondicao(Pessoa.class, "email = '"+SecurityContextHolder.getContext().getAuthentication().getName()+"'").get(0);
			return p;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
