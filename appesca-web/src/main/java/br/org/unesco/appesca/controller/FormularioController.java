package br.org.unesco.appesca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.unesco.appesca.service.FormularioService;
import br.org.unesco.appesca.service.UsuarioService;
import br.org.unesco.model.Formulario;
import br.org.unesco.model.Pergunta;
import br.org.unesco.model.Questao;
import br.org.unesco.model.Resposta;

@Model
@SessionScoped
public class FormularioController implements Serializable {
	private static final long serialVersionUID = 7096314126107579474L;

    @Inject
    private FormularioService formularioService;
    
    @Inject
    private UsuarioService usuarioService;

    @Produces
    @Named
    private List<Formulario> listaFormularios;
    
    private Formulario formulario;
  

    @PostConstruct
    public void inicializaNovoFormulario() {
    	try {
			listaFormularios = formularioService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String visualizar(Formulario formulario){
    	this.formulario = formulario;
    	return "visualizarFormulario";
    }
   
   
	public List<Formulario> getListaFormularios() {
		return listaFormularios;
	}

	public void setListaFormularios(List<Formulario> listaEntrevistado) {
		this.listaFormularios = listaEntrevistado;
	}

	public FormularioService getFormularioService() {
		return formularioService;
	}

	public void setFormularioService(FormularioService equipeService) {
		this.formularioService = equipeService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	
	public String getResposta(String chave){

		String indices[] = chave.split("_");
		String q = indices[0].substring(1);
		String p = indices[1].substring(1);
		String r = indices[2].substring(1);
		
		for (Questao questao : formulario.getListaQuestoes()) {
			if(q.equals(questao.getOrdem().intValue())){
				for (Pergunta pergunta : questao.getListaPerguntas()) {
					if(p.equals(pergunta.getOrdem().intValue())){
						for (Resposta resposta : pergunta.getListaRespostas()) {
							if(r.equals(resposta.getOpcao().intValue())){
								return resposta.getTexto();
							}
						}
					}
				}
			}
		}
		return "";
	}
	
}
