package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PlaylistDAO {

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	public PlaylistDAO() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Playlist> findAllPlaylists() {
		return (ArrayList<Playlist>) em.createQuery("SELECT p FROM Playlist p")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Playlist> findMyPlaylists(int id){
		return (ArrayList<Playlist>) em.createQuery("SELECT p FROM Playlist p WHERE p.utilizador.idUtilizador = :id")
				.setParameter("id", id)
				.getResultList();
	}
	
	public boolean newPlaylist(Playlist p) {
		//se conseguir guardar a musica manda true, senao manda false
		boolean existe=false;
		ArrayList<Playlist> lista = findAllPlaylists();
		for (Playlist pla:lista ){
			if (pla.getName().equals(p.getName())){
				existe=false;
			}else{
				existe = true;
			}
		}
		if (existe)
			em.merge(p);
		return existe;
	}

}
