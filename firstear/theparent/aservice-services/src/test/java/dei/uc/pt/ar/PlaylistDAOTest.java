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
public class PlaylistDAOTest {
	private static final int ID_PLAY = 0;
	private static final int ID_USER = 0;

	@InjectMocks
	PlaylistDAO pd;
	@Mock
	Query q;

	@Mock
	EntityManager em;

	@Test
	public void findAllPlaylistTest() {
		List<Playlist> play = null;
		when(em.createQuery("SELECT p FROM Playlist p")).thenReturn(q);
		when(q.getResultList()).thenReturn(play);

		play = pd.findAllPlaylists();
		verify(q).getResultList();
	}

	@Test
	public void findMyPlaylistTest() {
		List<Playlist> play = new ArrayList<Playlist>();
		Utilizador u = new Utilizador();
		u.setIdUtilizador(ID_USER);

		when(
				em.createQuery("SELECT p FROM Playlist p WHERE p.utilizador.idUtilizador = :id"))
				.thenReturn(q);
		when(q.setParameter("id", ID_USER)).thenReturn(q);
		when(q.getResultList()).thenReturn(play);
		play = pd.findMyPlaylists(ID_USER);
		verify(q).setParameter("id", ID_USER);
	}

	@Test
	public void getPlaylistTest() {
		Playlist p = new Playlist();
		p.setIdPlaylist(ID_PLAY);
		when(
				em.createQuery("SELECT p FROM Playlist p WHERE p.idPlaylist = :idplay"))
				.thenReturn(q);
		when(q.setParameter("idplay", ID_PLAY)).thenReturn(q);
		when(q.getSingleResult()).thenReturn(p);
		p = pd.getPlaylist(ID_PLAY);
		verify(q).getSingleResult();
		verify(q).setParameter("idplay", ID_PLAY);
	}

}
