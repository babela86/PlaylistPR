package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class MusicDAO {

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	public MusicDAO() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findAllMusic() {
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findMyMusic(int id){
		return (ArrayList<Musica>) em.createNativeQuery("SELECT m FROM Musica m WHERE m.idUtil LIKE :id")
				.setParameter("id", id)
				.getResultList();
	}

}
