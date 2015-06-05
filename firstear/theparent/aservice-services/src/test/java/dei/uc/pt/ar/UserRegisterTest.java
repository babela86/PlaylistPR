package dei.uc.pt.ar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterTest {

	@Mock
	Query query;
	@Mock
	EntityManager em;

	@InjectMocks
	MusicDAO md;

	@Test
	public void newUserTest() {
		Utilizador u;

	}

	// public String newUser(Utilizador u) throws NoSuchAlgorithmException,
	// UnsupportedEncodingException, ParseException {
	// q = em.createQuery("SELECT u FROM Utilizador u");
	// List<Utilizador> verifica = q.getResultList();
	// if (verifica.size() < 1)
	// pp.populando();
	// q = em.createQuery("SELECT u FROM Utilizador u");
	// List<Utilizador> results = q.getResultList();
	// boolean found = false;
	// String result;
	// for (Utilizador util : results) {
	// if (util.getEmail().equals(u.getEmail())) {
	// found = true;
	// }
	// }
	// if (found) {
	// // enviar msg de erro
	// result = "User e-mail already exists!";
	// } else {
	// try {
	// u.setPassword(encriptaPass(u.getPassword()));
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// em.persist(u);
	// result = "User added to DB";
	// }
	// return result;
	// }
}
