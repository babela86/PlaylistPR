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
public class LyricDAO {

	private static final Logger log = LoggerFactory.getLogger(MusicDAO.class);

	
	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	
	private Query query;
	
	public LyricDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Lyric> findAllLyrics() {
		return (ArrayList<Lyric>) em.createQuery("SELECT l FROM Lyric l")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Lyric> findMyLyric(int id) {
		return (ArrayList<Lyric>) em
				.createQuery(
						"SELECT l FROM Lyric l WHERE l.utilizador.idUtilizador = :id")
						.setParameter("id", id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Lyric> findMyLyricByMusic(int id) {
		try{
		query=em.createQuery(
						"SELECT l FROM Lyric l WHERE l.musica.idMusic = :id");
		
						query.setParameter("id", id);
						System.out.println(query.getResultList());
						return (ArrayList<Lyric>) query.getResultList();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void saveLyric(String lyric, Utilizador util, Musica m){
		Lyric l = new Lyric(lyric, util, m);
		em.persist(l);
	}
}
