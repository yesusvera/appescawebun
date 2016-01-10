package br.com.appesca.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;

import br.com.appesca.enums.PerfilEnum;

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
	
	public boolean eAdministrador(){
		if(usuarioLogado==null) return false;
		return usuarioLogado.getPerfil().equals(PerfilEnum.ADMINISTRADOR);
	}
	
	public boolean eCoordenador(){
		if(usuarioLogado==null) return false;
		return usuarioLogado.getPerfil().equals(PerfilEnum.COORDENADOR);
	}

	public boolean eDegravador(){
		if(usuarioLogado==null) return false;
		return usuarioLogado.getPerfil().equals(PerfilEnum.DEGRAVADOR);
	}

	public boolean ePesquisador(){
		if(usuarioLogado==null) return false;
		return usuarioLogado.getPerfil().equals(PerfilEnum.PESQUISADOR);
	}
}
