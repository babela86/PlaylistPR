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
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id")
				.setParameter("id", id)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findArtistMusic(String artist){
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m WHERE m.artist = :art")
				.setParameter("art", "%"+artist+"%")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findTitleMusic(String title){
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m WHERE m.title = :tit")
				.setParameter("tit", "%"+title+"%")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findArtistTitleMusic(String title, String artist){
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m WHERE m.title = :tit AND m.artist = :art")
				.setParameter("tit", "%"+title+"%")
				.setParameter("art", "%"+artist+"%")
				.getResultList();
	}
	
	

}
