package dei.uc.pt.ar.paj;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dei.uc.pt.ar.LogedUsers;
import dei.uc.pt.ar.Playlist;
import dei.uc.pt.ar.UserDAO;
import dei.uc.pt.ar.UserRegister;
import dei.uc.pt.ar.Utilizador;

@Stateless
@Path("/users")
public class SimpleUserService {

	@Inject
	private UserDAO ud;
	@Inject
	private UserRegister ur;
	@Inject
	private LogedUsers lu;

	//Listar todos os users
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public List<Utilizador> getAllUsers(){		
		return (List<Utilizador>) ud.findAllUsers();
	}
	
	//Listar user concreto
	@GET
	@Path("/list/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	public Utilizador getSimpleUserById(@PathParam("userId") int id){		
		return ud.findUserById(id);
	}
	
	//Remover user concreto
	@GET
	@Path("/delete/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response removeUserById(@PathParam("userId") int id){		
		boolean removed = ud.removeUser(id);
		if (removed)
			return Response.ok().build();
		else
			return Response.notModified().build();
	}
	
	//Adicionar user
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	public Response createUser(Utilizador user) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
		Utilizador another = new Utilizador();
		another.setEmail(user.getEmail());
		another.setName(user.getName());
		another.setPassword(user.getPassword());
		another.setBirthdate(user.getBirthdate());
		String srt = ur.newUser(another);
		
		if (srt.startsWith("User added")){
			return Response.ok().build();
		}else{
			return Response.notModified().build();
		}
	}
	
	//Change pass
	@POST
	@Path("/changepass/{utilId}")
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	public Response changePass(@PathParam("utilId") int id, Utilizador user){
		boolean sucess = ud.changePass(user.getPassword(), id);
		
		if (sucess){
			return Response.ok(ud.findUserById(id)).build();
		}else{
			return Response.notModified().build();
		}
	}
	
	//Listar todos os users logados
	@GET
	@Path("/listlogedusers")
	@Produces(MediaType.APPLICATION_XML)
	public List<Utilizador> getAllLogedUsers(){		
		return (List<Utilizador>) lu.getListalogados();
	}
	
	/*
	@POST
	@Path("/simpleuser")
	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	public Response createSimpleUser(SimpleUser user){
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		SimpleUser another = new SimpleUser();
		another.setUsername(user.getUsername());// Why ? :(
		
		
		SimpleUser newuser = usermng.create(another);
		//Response.notModified();
		
		return Response.ok(newuser).build();
		
	}
	*/
	
}
