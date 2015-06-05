package dei.uc.pt.ar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MusicDAOTest {

	@Mock
	Query query;
	@Mock
	EntityManager em;

	@InjectMocks
	MusicDAO md;

	@Test
	public void findArtistMusic() {
		String artist = "";

		when(em.createQuery("SELECT m FROM Musica m WHERE m.artist LIKE :art"))
				.thenReturn(query);

		md.findArtistMusic(artist);
		verify(query).setParameter("art", "%" + artist + "%");
		verify(query).getResultList();

		verify(em).createQuery(
				"SELECT m FROM Musica m WHERE m.artist LIKE :art");
	}

	@Test
	public void getMusic() {
		int idMus = 0;
		when(em.createQuery("select m from Musica m where m.idMusic = :idm"))
				.thenReturn(query);
		md.getMusic(idMus);
		verify(query).setParameter("idm", idMus);
		verify(query).getResultList();
	}

}
