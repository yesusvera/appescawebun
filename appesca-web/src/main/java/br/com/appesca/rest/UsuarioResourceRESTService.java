package br.com.appesca.rest;

import java.io.ByteArrayInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.appesca.data.UsuarioRepository;
import br.com.appesca.model.Usuario;
import br.com.appesca.rest.model.RespAutenticacaoREST;

/**
 * 
 * @author yesus
 *
 */
@Path("/usuario")
@RequestScoped
public class UsuarioResourceRESTService {
	@Inject
	private UsuarioRepository repository;

	@GET
	@Path("/autenticacao")
	@Produces(MediaType.APPLICATION_JSON)
	public RespAutenticacaoREST authenticate(@QueryParam("login") String login, @QueryParam("senha") String senha) {
		Usuario usr = repository.findByLoginSenha(login, senha);

		if (usr != null) {
			usr.setImagem(null);
			usr.setListaEquipes(null);
		}

		RespAutenticacaoREST rs = new RespAutenticacaoREST(usr, usr == null,
				usr == null ? "Usuário não encontrado!" : "");

		return rs;
	}

	@GET
	@Path("/imagem")
	@Produces("image/png")
	public Response imageUser(@QueryParam("login") String login, @QueryParam("senha") String senha) {
		Usuario usr = repository.findByLoginSenha(login, senha);
		if (usr == null) {
			return null;
		}
		return Response.ok(new ByteArrayInputStream(usr.getImagem())).build();
	}
}
