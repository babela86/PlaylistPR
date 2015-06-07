package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class UserDAO {

	private static final Logger log = LoggerFactory
			.getLogger(UserRegister.class);

	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	private Query q;

	public UserDAO() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Utilizador> findAllUsers() {
		return (ArrayList<Utilizador>) em.createQuery(
				"SELECT u FROM Utilizador u").getResultList();
	}

	public Utilizador findUserById(int id) {
		return (Utilizador) em
				.createQuery(
						"SELECT u FROM Utilizador u WHERE u.idUtilizador = :id")
				.setParameter("id", id).getSingleResult();
	}

	public boolean changeAccount(Utilizador u, Utilizador uact) {
		try {
			q = em.createQuery("UPDATE Utilizador SET name =:nome, birthdate =:dataNasc, email =:email, password =:password WHERE idUtilizador = :IdUtilAtivo");
			q.setParameter("IdUtilAtivo", uact.getIdUtilizador());
			q.setParameter("nome", u.getName());
			q.setParameter("dataNasc", u.getBirthdate());
			q.setParameter("email", u.getEmail());
			q.setParameter("password", u.getPassword());
			q.executeUpdate();
			log.info("Dados da conta alterados");
			return true;
		} catch (Exception e) {
			log.error("Dados da conta não alterados");
			return false;
		}
	}

	public boolean deleteAccount(Utilizador uact) {
		try {
			q = em.createQuery("UPDATE Musica m SET m.utilizador =1 WHERE m.utilizador =:utilact");
			q.setParameter("utilact", uact);
			q.executeUpdate();
			@SuppressWarnings("unchecked")
			ArrayList<Playlist> lista = (ArrayList<Playlist>) em
					.createQuery(
							"SELECT p FROM Playlist p WHERE p.utilizador.idUtilizador = :id")
					.setParameter("id", uact.getIdUtilizador()).getResultList();
			for (Playlist p : lista) {
				em.remove(em.merge(p));
			}
			em.remove(em.merge(uact));
			log.info("Dados da conta apagados!");
			return true;
		} catch (Exception e) {
			log.error("Problema ao apagar dados da conta!");
			return false;
		}
	}

}
