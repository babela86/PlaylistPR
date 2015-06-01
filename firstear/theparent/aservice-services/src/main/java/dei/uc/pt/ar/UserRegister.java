package dei.uc.pt.ar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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


	public String newUser(Utilizador u) {
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		boolean found=false;
		String result; 
		for (Utilizador util: results ){
			if (util.getEmail().equals(u.getEmail())){
				found=true;
			}
		}

		if (found){
			//enviar msg de erro			
			result="E-mail de utilizador já existente!";
		}else{
			try {
				u.setPassword(encriptaPass(u.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			em.persist(u);
			result="Utilizador adicionado à base de dados";
		}
		return result;
	}
	
	public Utilizador loginUser(String email, String pass) {
		//Se conseguir encontrar o user devolve true, caso contrário false
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		try {
			pass = encriptaPass(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (Utilizador util: results){
			if (util.getEmail().equals(email)&&util.getPassword().equals(pass)){	
				return util;
			}
		}
		return null;
	}
	
	public String encriptaPass(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest("senha".getBytes("UTF-8"));
		
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}

}
