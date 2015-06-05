package dei.uc.pt.ar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistDAOTest {

	@Mock
	Query query;
	@Mock
	EntityManager em;

	@InjectMocks
	PlaylistDAO pd;

	// @Test
	// public void findMyPlaylists(){
	// int id=;

}
// @SuppressWarnings("unchecked")
// public ArrayList<Playlist> findMyPlaylists(int id){
// return (ArrayList<Playlist>)
// em.createQuery("SELECT p FROM Playlist p WHERE p.utilizador.idUtilizador = :id")
// .setParameter("id", id)
// .getResultList();
// }

