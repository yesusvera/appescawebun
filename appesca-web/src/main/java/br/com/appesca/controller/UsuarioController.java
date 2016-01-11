package br.com.appesca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.appesca.enums.PerfilEnum;
import br.com.appesca.model.Identidade;
import br.com.appesca.model.Usuario;
import br.com.appesca.service.UsuarioService;

@Model
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 7096314126107579474L;

	@Inject
    private FacesContext facesContext;

    @Inject
    private UsuarioService usuarioService;

    @Produces
    @Named
    private List<Usuario> listaUsuarios;
    
    private Usuario usuario = new Usuario();
    
    private String strPerfil;
    
    private String confirmacaoSenha;
    
    @Inject Identidade identidade;
    

    @PostConstruct
    public void inicializaNovoUsuario() {
    	try {
			listaUsuarios = usuarioService.listAll();
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public String salvar() throws Exception{
    	usuarioService.save(usuario);
    	listaUsuarios = usuarioService.listAll();
    	return "usuarios";
    }

    public String cadastrarUsuario(){
    	usuario = new Usuario();
    	return "cadastrarUsuario";
    }
    
    public String editar(Usuario usr){
    	this.usuario = usr;
    	this.confirmacaoSenha = usr.getSenha();
    	return "cadastrarUsuario";
    }
    
    //Tirar outra foto
    public void tirarOutra(){
        usuario.setImagem(null);
    }
    
    public void oncapture(CaptureEvent captureEvent) {
        byte[] data = captureEvent.getData();
        usuario.setImagem(data);
    }
    
    public StreamedContent getDynamicImage() {

		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");
		if (id != null && listaUsuarios!=null && !listaUsuarios.isEmpty()) {
			for (Usuario usrTmp : listaUsuarios) {
				if (usrTmp.getId().intValue() == Integer.valueOf(id)) {
					return usrTmp.getImageGraphics();
				}
			}
		}

		 
		 return new DefaultStreamedContent();
	 }

    public List<SelectItem> listaPerfis() {
    	List<SelectItem> items = new ArrayList<>();
        for(PerfilEnum g: PerfilEnum.values()) {
          if(g.equals(PerfilEnum.ADMINISTRADOR) && !identidade.eAdministrador()){
        	  continue;
          }
          items.add(new SelectItem(g, g.getDescricao()));
        }
        return items;
      }
    
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getStrPerfil() {
		return strPerfil;
	}

	public void setStrPerfil(String strPerfil) {
		this.strPerfil = strPerfil;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
}