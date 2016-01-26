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

	@Inject
	Identidade identidade;

	private Resposta problemasF1Q72;

	private Resposta solucoesF1Q72;

	private Resposta problemasF2Q60;

	private Resposta solucoesF2Q60;

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

		switch (formulario.getIdTipoFormulario()) {
		case 1:
			carregarRespostasEditaveisBranco();
			return "formCamaraoPiticaiaEBranco?faces-redirect=true";
		case 2:

			break;
		case 3:
			carregarRespostasEditaveisRegional();
			return "formCamaraoPiticaiaEBranco?faces-redirect=true";
		}
		return "formCamaraoRegional?faces-redirect=true";

	}

	private void carregarRespostasEditaveisRegional() {
		this.problemasF1Q72 = getResposta("q72_p1_r1");
		this.solucoesF1Q72 = getResposta("q72_p1_r2");

	}

	private void carregarRespostasEditaveisBranco() {
		this.problemasF2Q60 = getResposta("q60_p1_r1");
		this.solucoesF2Q60 = getResposta("q60_p1_r2");
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
			respostaService.save(resp);
			addMessage("Alteração realizada com sucesso!!");
		} catch (Exception e) {
			addMessage("Erro ao salvar a resposta!!");
		}

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Resposta getProblemasF1Q72() {
		return problemasF1Q72;
	}

	public void setProblemasF1Q72(Resposta problemasF1Q72) {
		this.problemasF1Q72 = problemasF1Q72;
	}

	public Resposta getSolucoesF1Q72() {
		return solucoesF1Q72;
	}

	public void setSolucoesF1Q72(Resposta solucoesF1Q72) {
		this.solucoesF1Q72 = solucoesF1Q72;
	}

	public Resposta getProblemasF2Q60() {
		return problemasF2Q60;
	}

	public void setProblemasF2Q60(Resposta problemasF2Q60) {
		this.problemasF2Q60 = problemasF2Q60;
	}

	public Resposta getSolucoesF2Q60() {
		return solucoesF2Q60;
	}

	public void setSolucoesF2Q60(Resposta solucoesF2Q60) {
		this.solucoesF2Q60 = solucoesF2Q60;
	}

}
