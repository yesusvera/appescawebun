package br.com.appesca.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

@Model
@SessionScoped
public class Identidade implements Serializable{

	private static final long serialVersionUID = 6566848023742180390L;

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getLabelUserButton(){
		
		if(usuarioLogado!=null){
			return usuarioLogado.getNome() + "(" + usuarioLogado.getPerfil() + ")";
		}
		
		return "";
	}

}
