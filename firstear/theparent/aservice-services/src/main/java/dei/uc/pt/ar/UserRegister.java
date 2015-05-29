package dei.uc.pt.ar;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserRegister {

	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	private Query q;

	public String newUser(String email, String name, String pass, Date birthday) {
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		boolean found=false;
		String result; 
		for (Utilizador util: results ){
			if (util.getEmail().equals(email)){
				found=true;
			}
		}
//		Query q = em.createQuery("select count(*) from Utilizador u where u." + campo_t + " = :nome_t");
//		q.setParameter("nome_t", nome_t);
//		String res = q.getSingleResult().toString();
//		return res;
		
		if (found){
			//enviar msg de erro			
			result="E-mail de utilizador já existente!";

		}else{
			Utilizador u = new Utilizador(email, name, pass, birthday);
			em.persist(u);
			result="Utilizador adicionado à base de dados";

		}
		return result;
	}

}
