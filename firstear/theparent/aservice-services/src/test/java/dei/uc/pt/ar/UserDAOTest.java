package dei.uc.pt.ar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

	private static final int ID_USER = 0;
	@Mock
	Query q;
	@Mock
	EntityManager em;

	@InjectMocks
	UserDAO ud;

	@Test
	public void findAllUsersTest() {
		List<Utilizador> user = null;
		when(em.createQuery("SELECT u FROM Utilizador u")).thenReturn(q);
		when(q.getResultList()).thenReturn(user);

		user = ud.findAllUsers();
		verify(q).getResultList();
	}

	@Test
	public void finsUserByISTest() {
		Utilizador u = new Utilizador();
		u.setIdUtilizador(ID_USER);
		when(
				em.createQuery("SELECT u FROM Utilizador u WHERE u.idUtilizador = :id"))
				.thenReturn(q);
		when(q.setParameter("id", ID_USER)).thenReturn(q);
		when(q.getSingleResult()).thenReturn(u);
		u = ud.findUserById(ID_USER);
		verify(q).getSingleResult();
		verify(q).setParameter("id", ID_USER);
	}

}
