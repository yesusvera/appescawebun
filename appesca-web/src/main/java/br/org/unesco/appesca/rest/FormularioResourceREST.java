package br.org.unesco.appesca.rest;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.org.unesco.appesca.data.FormularioRepository;
import br.org.unesco.appesca.data.UsuarioRepository;
import br.org.unesco.appesca.rest.model.RespAutenticacaoREST;
import br.org.unesco.appesca.rest.model.RespFormularioREST;
import br.org.unesco.model.Formulario;
import br.org.unesco.model.IdentEntrevistado;
import br.org.unesco.model.Usuario;

/**
 * @author yesus
 */
@Path("/formulario")
@RequestScoped
public class FormularioResourceREST extends BaseREST{
	@Inject
	private FormularioRepository formularioRepository;
	
	@Inject
	private UsuarioRepository usuarioRespository;

	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_XML)
	public String listaPorLogin(@QueryParam("login") String login, @QueryParam("senha") String senha) {
		RespFormularioREST resp = new RespFormularioREST();
		
		Usuario usr = usuarioRespository.findByLoginSenha(login, senha);

		List<Formulario> listaFormularios = formularioRepository.findListByUsuario(usr);

		if(listaFormularios!=null && listaFormularios.size() > 0){
			resp.setListaFormularios(listaFormularios);
		}else{
			resp.setErro(true);
			resp.setMensagemErro("Não existem registros para este usuário");
		}

		return getXML(resp);
	}
	
	@GET
	@Path("/insert")
	@Produces(MediaType.APPLICATION_XML)
	public String authenticate(@QueryParam("login") String login,
							@QueryParam("senha") String senha,
							@QueryParam("uf") String uf,
							@QueryParam("sexo") String sexo,
							@QueryParam("nome") String nome) {
		
		Usuario usr = usuarioRespository.findByLoginSenha(login, senha);
		
		IdentEntrevistado ie = new IdentEntrevistado();
	
		ie.setNomeCompleto(nome);
		ie.setSexo(sexo);
		
		Formulario formulario = new Formulario(nome, 1, usr.getId(), new Date());
		formulario.setEntrevistado(ie);
		formularioRepository.save(formulario);
		
		RespAutenticacaoREST rs = new RespAutenticacaoREST();
		rs.setErro(false);
		rs.setMensagemErro("Formulário cadastrado!");

		return getXML(rs);
	}
}
