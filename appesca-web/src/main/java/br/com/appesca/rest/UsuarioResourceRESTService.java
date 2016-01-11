package br.com.appesca.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.appesca.data.UsuarioRepository;
import br.com.appesca.model.Usuario;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the usuario table.
 */
@Path("/usuarios")
@RequestScoped
public class UsuarioResourceRESTService {

    @Inject
    private UsuarioRepository repository;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Usuario> authenticate(
//    			@QueryParam String login, 
//    			@PathParam("senha") String senha) {
//    	List<Usuario> listaUsuarios = repository.listAll();
//    	
//    	for(Usuario usr: listaUsuarios){
//    		usr.setImagem(null);
//    	}
//        return listaUsuarios;
//    }

}










