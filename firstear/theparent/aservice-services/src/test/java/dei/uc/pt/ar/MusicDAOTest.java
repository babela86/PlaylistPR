package dei.uc.pt.ar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MusicDAOTest {
	private static final int ID_PLAY = 0;

	private static final int ID_MUS = 0;
	@Mock
	Query q;
	@Mock
	EntityManager em;
	@Mock
	Playlist playlist;

	@InjectMocks
	MusicDAO md;

	@Test
	public void findAllMusicsTest() {
		List<Musica> mus = null;
		when(em.createQuery("SELECT m FROM Musica m")).thenReturn(q);
		when(q.getResultList()).thenReturn(mus);

		mus = md.findAllMusic();
		verify(q).getResultList();
	}

	@Test
	public void MusicByTitleDescTest() {
		List<Musica> mus = null;
		when(em.createQuery("SELECT m FROM Musica m ORDER BY m.title DESC"))
		.thenReturn(q);
		when(q.getResultList()).thenReturn(mus);
		mus = md.MusicByTitleDesc();
		verify(q).getResultList();

	}

	@Test
	public void getMusicTest() {
		Musica mus = new Musica();
		mus.setIdMusic(ID_MUS);
		when(em.createQuery("SELECT m FROM Musica m WHERE m.idMusic = :idm"))
		.thenReturn(q);
		when(q.setParameter("idm", ID_MUS)).thenReturn(q);
		when(q.getSingleResult()).thenReturn(mus);
		mus = md.getMusic(ID_MUS);
		verify(q).setParameter("idm", ID_MUS);
	}

	@Test
	public void listMusicasPlaylistTest() {
		List<Musica> mus = new ArrayList<Musica>();
		Playlist p = new Playlist();
		p.setIdPlaylist(ID_PLAY);
		when(
				em.createQuery("SELECT m FROM Playlist p JOIN p.musicas m WHERE p.idPlaylist = :idPlay"))
				.thenReturn(q);
		when(q.setParameter("idPlay", ID_PLAY)).thenReturn(q);
		when(q.getResultList()).thenReturn(mus);
		mus = md.listMusicasPlaylist(ID_PLAY);
		verify(q).getResultList();
		verify(q).setParameter("idPlay", ID_PLAY);

	}

}
