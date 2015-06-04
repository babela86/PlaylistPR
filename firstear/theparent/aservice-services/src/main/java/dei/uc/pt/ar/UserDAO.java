package dei.uc.pt.ar;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UserDAO {

	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	Query q;

	public UserDAO() {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Utilizador> findAllUsers() {
		return (ArrayList<Utilizador>) em.createQuery("SELECT u FROM Utilizador u")
				.getResultList();
	}
	
	public Utilizador findUserById(int id){
		return (Utilizador) em.createQuery("SELECT u FROM Utilizador u WHERE u.idUtilizador = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public boolean changeAccount(Utilizador u, Utilizador uact){
		try{
			q = em.createQuery("UPDATE Utilizador SET name =:nome, birthdate =:dataNasc, email =:email, password =:password WHERE idUtilizador = :IdUtilAtivo");
			q.setParameter("IdUtilAtivo", uact.getIdUtilizador());
			q.setParameter("nome", u.getName());
			q.setParameter("dataNasc", u.getBirthdate());
			q.setParameter("email", u.getEmail());
			q.setParameter("password", u.getPassword());
			q.executeUpdate();
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	public boolean deleteAccount(Utilizador u, Utilizador uact){
		try{
		
			return true;
		}catch (Exception e){
			return false;
		}
	}

}
