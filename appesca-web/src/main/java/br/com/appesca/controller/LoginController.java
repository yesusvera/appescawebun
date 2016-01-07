package br.com.appesca.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.appesca.model.Usuario;
import br.com.appesca.service.LoginService;

@Model
public class LoginController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private LoginService loginService;

    @Produces
    @Named
    private Usuario usuario;

    @PostConstruct
    public void inicializaNovoUsuario() {
    	usuario = new Usuario();
    }

    public String login() throws Exception {
        try {

        	Usuario usrTmp = loginService.autenticar(usuario.getLogin(), usuario.getSenha());
        	
        	if(usrTmp!=null){
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Autenticado!", "Autenticado com sucesso.");
	            facesContext.addMessage(null, m);
	            return "visaoFormularios";
        	}else{
        		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", "Usuário ou senha inválidos.");
 	            facesContext.addMessage(null, m);
        	}
        	
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Erro ao fazer o login.");
            facesContext.addMessage(null, m);
        }
        
        inicializaNovoUsuario();
        
        return "";
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Erro ao efetuar login.";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
