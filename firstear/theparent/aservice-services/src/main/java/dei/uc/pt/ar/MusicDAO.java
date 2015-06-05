package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class MusicDAO {

	@PersistenceContext(name = "Playlist")
	EntityManager em;

	Query query;

	public MusicDAO() {
		super();
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

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> listMusicasPlaylist(int idPlay) {
		return (ArrayList<Musica>) em
				.createQuery(
						"select m from Playlist p join p.musicas m where p.idPlaylist = :idPlay")
						.setParameter("idPlay", idPlay).getResultList();
	}

	public Musica getMusic(int idMus) {
		return (Musica) em
				.createQuery("select m from Musica m where m.idMusic = :idm")
				.setParameter("idm", idMus).getSingleResult();

	}

	public Playlist getPlaylist(int idPlay) {
		return (Playlist) em
				.createQuery(
						"select p from Playlist p where p.idPlaylist = :idplay")
						.setParameter("idplay", idPlay).getSingleResult();

	}

	public String getPlaylistName(int idPlay) {
		return (String) em
				.createQuery(
						"select p.name from Playlist p where p.idPlaylist = :idplay")
						.setParameter("idplay", idPlay).getSingleResult();

	}

	public boolean addTo(int idPlay, int idMus) {
		boolean existe = false;
		Musica m = getMusic(idMus);
		Playlist p = getPlaylist(idPlay);

		ArrayList<Musica> lista = listMusicasPlaylist(idPlay);

		for (Musica mus : lista) {
			if (mus.getTitle().equals(m.getTitle())
					|| (mus.getPath().equals(m.getPath()))) {
				existe = false;
			} else {
				existe = true;
			}
		}
		if (existe) {
			p.addMusica(m);
			em.merge(m);
		}
		return existe;

	}

	public boolean deleteMusic(int idPlay, int idMus) {

		Musica m = getMusic(idMus);
		Playlist p = getPlaylist(idPlay);
		System.out.println(p);
		p.removeMusica(idMus);
		return true;
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
