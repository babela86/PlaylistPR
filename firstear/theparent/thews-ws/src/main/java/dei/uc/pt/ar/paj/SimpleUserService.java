package dei.uc.pt.ar.paj;

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
@Path("/simpleusers")
public class SimpleUserService {

	@Inject
	private UserDAO ud;

	@Inject
	private UserRegister ur;
	@Inject
	private LogedUsers lu;

	//Listar todos os users
	@GET
	@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
	public String getAllInString(){
		// not the way ! just for test.. 
		
		ArrayList<Utilizador> usr_list = new ArrayList<Utilizador>();
		usr_list.addAll(ud.findAllUsers());

		StringBuilder sb = new StringBuilder();

		for (Utilizador usr : usr_list)
			sb.append(usr.toString()).append(" ; ");
		
		return sb.toString();
	}
	
	
	@GET
	@Path("{suid: \\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
	public Utilizador getSimpleUserById(@PathParam("suid") int id){
		// use logs!!! (im lazy)
		System.out.println("get me : "+id);
		
		return ud.findUserById(id);
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
