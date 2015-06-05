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
	public ArrayList<Musica> findMyMusic(int id) {
		return (ArrayList<Musica>) em
				.createQuery(
						"SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findArtistMusic(String artist) {
		return (ArrayList<Musica>) em
				.createQuery("SELECT m FROM Musica m WHERE m.artist LIKE :art")
				.setParameter("art", "%" + artist + "%").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findTitleMusic(String title) {
		return (ArrayList<Musica>) em
				.createQuery("SELECT m FROM Musica m WHERE m.title LIKE :tit")
				.setParameter("tit", "%" + title + "%").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findArtistTitleMusic(String title, String artist) {
		return (ArrayList<Musica>) em
				.createQuery(
						"SELECT m FROM Musica m WHERE m.title LIKE :tit AND m.artist LIKE :art")
				.setParameter("tit", "%" + title + "%")
				.setParameter("art", "%" + artist + "%").getResultList();
	}

	public boolean newMusic(Musica m) {
		// se conseguir guardar a musica manda true, senao manda false
		boolean existe = false;
		ArrayList<Musica> lista = findAllMusic();
		for (Musica mus : lista) {
			if (mus.getTitle().equals(m.getTitle())
					|| (mus.getPath().equals(m.getPath()))) {
				existe = false;
			} else {
				existe = true;
			}
		}
		if (existe)
			em.merge(m);

		System.out.println(m.getIdMusic() + " dentro musica DAO");
		return existe;
	}

	public void deleteMusic(int Musica) {
		// fazer query
	}

	// public boolean updatePath(String path, int idMusica) {
	// try {
	// q =
	// em.createQuery("UPDATE Musica SET path =:path WHERE idMusic = :idMusica");
	// q.setParameter("path", path);
	// q.setParameter("idMusica", idMusica);
	// q.executeUpdate();
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

}
