package br.org.unesco.appesca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.unesco.appesca.service.FormularioService;
import br.org.unesco.appesca.service.RespostaService;
import br.org.unesco.appesca.service.UsuarioService;
import br.org.unesco.model.Formulario;
import br.org.unesco.model.Identidade;
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
	private RespostaService respostaService;

	@Inject
	private UsuarioService usuarioService;

	@Produces
	@Named
	private List<Formulario> listaFormularios;

	private Formulario formulario;
	
	@Inject Identidade identidade;
	
	private String textoReposta;

	@PostConstruct
	public void inicializaNovoFormulario() {
		try {
			listaFormularios = formularioService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String visualizar(Formulario formulario) {
		this.formulario = formulario;
		this.textoReposta = getResposta("q10_p2_r8").getTexto();
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

	public Resposta getResposta(String chave) {
		try {
			String indices[] = chave.split("_");
			String q = indices[0].substring(1);
			String p = indices[1].substring(1);
			String r = indices[2].substring(1);

			for (Questao questao : formulario.getListaQuestoes()) {
				if (q.equals(questao.getOrdem().toString())) {
					for (Pergunta pergunta : questao.getListaPerguntas()) {
						if (p.equals(pergunta.getOrdem().toString())) {
							if (pergunta.getBooleana()) {
								Resposta respBoleana = new Resposta();
								respBoleana.setPergunta(pergunta);
								return respBoleana;
							}
							for (Resposta resposta : pergunta.getListaRespostas()) {
								if (r.equals(resposta.getOpcao().toString())) {
									return resposta;
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resposta();
	}
	
	public List<Resposta> getListaRespostas(String chave) {
		try {
			String indices[] = chave.split("_");
			String q = indices[0].substring(1);
			String p = indices[1].substring(1);

			for (Questao questao : formulario.getListaQuestoes()) {
				if (q.equals(questao.getOrdem().toString())) {
					for (Pergunta pergunta : questao.getListaPerguntas()) {
						if (p.equals(pergunta.getOrdem().toString())) {
							return pergunta.getListaRespostas();
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Resposta>();
	}
	
	
	public void salvarResposta(Resposta resp) {
		try {
			resp.setTexto(textoReposta);
			respostaService.save(resp);
	        addMessage("Alteração realizada com sucesso!!");
		} catch (Exception e) {
			addMessage("Erro ao salvar a resposta!!");
		}
		
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public String getTextoReposta() {
		return textoReposta;
	}

	public void setTextoReposta(String textoReposta) {
		this.textoReposta = textoReposta;
	}

}
