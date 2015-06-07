package dei.uc.pt.ar;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class MusicDAO {

	private static final Logger log = LoggerFactory.getLogger(MusicDAO.class);

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	private Query query;
	@Inject
	PlaylistDAO pd;

	public MusicDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findAllMusic() {
		return (ArrayList<Musica>) em.createQuery("SELECT m FROM Musica m")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByTitleDesc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.title DESC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByTitleAsc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.title ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByArtistDesc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.artist DESC")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByArtistAsc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.artist ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByAlbumDesc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.album DESC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByAlbumAsc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.album ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByYearAsc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.year ASC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> MusicByYearDesc() {
		return (ArrayList<Musica>) em.createQuery(
				"SELECT m FROM Musica m ORDER BY m.year DESC").getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> findMyMusic(int id) {
		return (ArrayList<Musica>) em
				.createQuery(
						"SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id")
						.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Musica> findMyMusic2(int id) {
		query = em
				.createQuery("SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id");
		query.setParameter("id", id);
		return query.getResultList();
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
		log.info("Nova música adicionada à BD");
		return existe;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Musica> listMusicasPlaylist(int idPlay) {
		return (ArrayList<Musica>) em
				.createQuery(
						"SELECT m FROM Playlist p JOIN p.musicas m WHERE p.idPlaylist = :idPlay")
						.setParameter("idPlay", idPlay).getResultList();
	}

	public Musica getMusic(int idMus) {
		return (Musica) em
				.createQuery("SELECT m FROM Musica m WHERE m.idMusic = :idm")
				.setParameter("idm", idMus).getSingleResult();

	}

	public boolean addTo(int idPlay, int idMus) {
		try {
			Musica m = getMusic(idMus);
			Playlist p = pd.getPlaylist(idPlay);
			p.addMusica(m);
			em.merge(m);
			log.info("Música adicionada à Playlist");
			return true;
		} catch (Exception e) {
			log.error("Música não adicionada à Playlist");
			return false;
		}

	}

	public boolean deleteMusic(int idPlay, int idMus) {
		try {
			Musica m = getMusic(idMus);
			Playlist p = pd.getPlaylist(idPlay);
			p.removeMusica(idMus);
			em.merge(m);
			log.info("Música retirada da Playlist");
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	public boolean deleteMusicFromDb(int idMusic) {
		try {
			query = em
					.createQuery("UPDATE Musica m SET m.utilizador =1 WHERE m.idMusic =:idMusic");
			query.setParameter("idMusic", idMusic);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changeMusic(Musica m, int idMusic) {
		try {

			query = em
					.createQuery("UPDATE Musica m SET m.title =:title, m.album =:album, m.artist =:artist, m.year =:year WHERE m.idMusic = :idMusic");
			query.setParameter("title", m.getTitle());
			query.setParameter("album", m.getAlbum());
			query.setParameter("artist", m.getArtist());
			query.setParameter("year", m.getYear());

			query.setParameter("idMusic", idMusic);
			query.executeUpdate();
			log.info("Music updated");
			return true;
		} catch (Exception e) {
			log.error("Problem updating music");
			return false;
		}
	}
}
