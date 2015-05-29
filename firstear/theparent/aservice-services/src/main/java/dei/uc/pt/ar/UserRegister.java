package dei.uc.pt.ar;

import java.util.List;
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
	private Utilizador activeUSer;
	private boolean userLoged=false;

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
	
	public boolean loginUser(String email, String pass) {
		//Se conseguir encontrar o user devolve true, caso contrário false
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		boolean check = false;
		for (Utilizador util: results){
			if (util.getEmail().equals(email)&&util.getPassword().equals(pass)){
				check=true;
				this.activeUSer = util;
				this.userLoged = true;
			}
		}
		return check;
	}

	public Utilizador getActiveUSer() {
		return activeUSer;
	}

	public void setActiveUSer(Utilizador activeUSer) {
		this.activeUSer = activeUSer;
	}

	public boolean isUserLoged() {
		return userLoged;
	}

	public void setUserLoged(boolean userLoged) {
		this.userLoged = userLoged;
	}

}
