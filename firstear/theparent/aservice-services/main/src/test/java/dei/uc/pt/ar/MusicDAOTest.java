package dei.uc.pt.ar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MusicDAOTest {

	@Mock
	EntityManager em;
	@Mock
	Query query;
	@Mock
	TypedQuery<Musica> tm;

	@InjectMocks
	MusicDAO md;

	@Test
	public void findMyMusic3() {
		int id = 0;

		when(
				em.createQuery("SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id"))
				.thenReturn(query);
		md.findMyMusic2(id);

		verify(query).setParameter("id", id);
	}

	@Ignore
	@Test
	public void findMyMusic() {
		// ArrayList<Musica> m = (ArrayList<Musica>) tm.getResultList();
		ArrayList<Musica> m = null;
		int id = 3;
		when(
				em.createQuery("SELECT m FROM Musica m WHERE m.utilizador.idUtilizador = :id"))
				.thenReturn(tm);
		when(tm.getResultList()).thenReturn(m);
		m = md.findMyMusic(id);
		verify(tm).getResultList();

	}

	// @Test
	// public void getMusicTest() {
	// Musica mus=null;
	// int id = 0;
	// when(
	// em.createQuery("select m from Musica m where m.idMusic = :idm")).thenReturn(tm);
	// when()
	// }
	// public Musica getMusic(int idMus) {
	// return (Musica) em
	// .createQuery("select m from Musica m where m.idMusic = :idm")
	// .setParameter("idm", idMus).getSingleResult();
	//
	// }

}
