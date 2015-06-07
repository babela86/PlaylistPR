package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class PlaylistDAO {

	private static final Logger log = LoggerFactory
			.getLogger(PlaylistDAO.class);

	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	private Query query;

	public PlaylistDAO() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Playlist> findAllPlaylists() {
		return (ArrayList<Playlist>) em.createQuery("SELECT p FROM Playlist p")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Playlist> findMyPlaylists(int id) {
		return (ArrayList<Playlist>) em
				.createQuery(
						"SELECT p FROM Playlist p WHERE p.utilizador.idUtilizador = :id")
				.setParameter("id", id).getResultList();
	}

	public Playlist getPlaylist(int idPlay) {
		return (Playlist) em
				.createQuery(
						"SELECT p FROM Playlist p WHERE p.idPlaylist = :idplay")
				.setParameter("idplay", idPlay).getSingleResult();

	}

	public String getPlaylistName(int idPlay) {
		return (String) em
				.createQuery(
						"select p.name from Playlist p where p.idPlaylist = :idplay")
				.setParameter("idplay", idPlay).getSingleResult();

	}

	public boolean newPlaylist(Playlist p) {
		// se conseguir guardar a musica manda true, senao manda false
		boolean existe = false;
		ArrayList<Playlist> lista = findAllPlaylists();
		for (Playlist pla : lista) {
			if (pla.getName().equals(p.getName())) {
				existe = false;
			} else {
				existe = true;
			}
		}
		if (existe)
			em.merge(p);
		log.info("Nova Playlist criada!");
		return existe;
	}

	public boolean deletePlaylist(int idPlay) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Playlist> lista = (ArrayList<Playlist>) em
					.createQuery(
							"SELECT p FROM Playlist p WHERE p.idPlaylist = :id")
					.setParameter("id", idPlay).getResultList();
			for (Playlist p : lista) {
				em.remove(em.merge(p));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changePlaylist(String name, int idPlay) {
		// try{
		query = em
				.createQuery("UPDATE Playlist p SET p.name =:name WHERE p.idPlaylist = :idPlay");
		query.setParameter("name", name);
		query.setParameter("idPlay", idPlay);
		query.executeUpdate();
		log.info("Playlist updated");
		return true;
		// }catch (Exception e){
		// log.error("Problem updating playlist");
		// return false;
		// }
	}

}
