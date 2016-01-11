package br.com.appesca.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.appesca.data.FormularioRepository;
import br.com.appesca.data.UsuarioRepository;
import br.com.appesca.enums.PerfilEnum;
import br.com.appesca.model.Usuario;
import br.com.appesca.rest.model.RespFormularioREST;

/**
 * @author yesus
 */
@Path("/formulario")
@RequestScoped
public class FormularioResourceREST {
	@Inject
	private FormularioRepository formularioRepository;
	
	@Inject
	private UsuarioRepository usuarioRespository;

	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_JSON)
	public RespFormularioREST authenticate(@QueryParam("login") String login, @QueryParam("senha") String senha) {
		RespFormularioREST resp = new RespFormularioREST();
		
		Usuario usr = usuarioRespository.findByLoginSenha(login, senha);

		if(usr.getPerfil().equals(PerfilEnum.PESQUISADOR)){
			resp.setListaFormularios(formularioRepository.findListByUsuario(usr));
		}else{
			resp.setErro(true);
			resp.setMensagemErro("Não existem registros para este usuário");
		}

		return resp;
	}
}
