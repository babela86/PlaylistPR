package dei.uc.pt.ar.paj;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dei.uc.pt.ar.MusicDAO;
import dei.uc.pt.ar.Musica;
import dei.uc.pt.ar.Playlist;
import dei.uc.pt.ar.PlaylistDAO;
import dei.uc.pt.ar.Utilizador;


@Stateless
@Path("/playlists")
public class SimplePlaylistService {
	
	@Inject
	private PlaylistDAO pd;
	
	//Listar todas as playlists
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_XML})
	public List<Playlist> getAllPlaylists(){
		
		return pd.findAllPlaylists();
		
	}
	
	//Playlists de um utilizador concreto
	@GET
	@Path("/fromuser/{userId}")
	@Produces({MediaType.APPLICATION_XML})
	public List<Playlist> getPlaylistFromUser(@PathParam("userId") int id){		
		return pd.findMyPlaylists(id);
	}
	
	//Listar uma playlist concreta
	@GET
	@Path("/list/{playId}")
	@Produces({MediaType.APPLICATION_XML})
	public Playlist getPlaylistById(@PathParam("playId") int id){		
		return pd.getPlaylist(id);
	}

}
