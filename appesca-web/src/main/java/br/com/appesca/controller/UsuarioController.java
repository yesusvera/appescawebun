package br.com.appesca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.appesca.model.Identidade;
import br.com.appesca.model.Usuario;
import br.com.appesca.service.UsuarioService;

@Model
@RequestScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 7096314126107579474L;

	@Inject
    private FacesContext facesContext;

    @Inject
    private UsuarioService usuarioService;

    @Produces
    @Named
    private List<Usuario> listaUsuarios;
    
    @Inject Identidade identidade;

    @PostConstruct
    public void inicializaNovoUsuario() {
    	try {
			listaUsuarios = usuarioService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}
