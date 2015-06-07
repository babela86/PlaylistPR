package dei.uc.pt.ar;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class UserRegister {

	private static final Logger log = LoggerFactory
			.getLogger(UserRegister.class);

	@PersistenceContext(name = "Playlist")
	private EntityManager em;
	private Query q;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	@Inject
	private Populate pp;

	@SuppressWarnings("unchecked")
	public String newUser(Utilizador u) throws NoSuchAlgorithmException,
	UnsupportedEncodingException, ParseException {
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> verifica = q.getResultList();
		if (verifica.size() < 1)
			pp.populando();
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		boolean found = false;
		String result;
		for (Utilizador util : results) {
			if (util.getEmail().equals(u.getEmail())) {
				log.info("Já havia um user com o email dado.");

				found = true;
			}
		}
		if (found) {
			// enviar msg de erro
			result = "User e-mail already exists!";
		} else {
			try {
				log.info("User com password:" + u.getPassword());
				u.setPassword(encriptaPass(u.getPassword()));
				log.info("User com password encriptada:" + u.getPassword());
			} catch (NoSuchAlgorithmException e) {
				log.error("Erro na encriptação.");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				log.error("Erro na encriptação.");
				e.printStackTrace();
			}
			em.persist(u);
			result = "User added to DB";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Utilizador loginUser(String email, String pass)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			ParseException {
		// Se conseguir encontrar o user devolve-o, caso contrário devolve null
		String senha = "";
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> verifica = q.getResultList();
		if (verifica.size() < 2) {
			pp.populando();
		}
		q = em.createQuery("SELECT u FROM Utilizador u");
		List<Utilizador> results = q.getResultList();
		try {
			senha = encriptaPass(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (Utilizador util : results) {
			if (util.getEmail().equals(email)
					&& util.getPassword().equals(senha)) {
				log.info("Login sucedido!");

				return util;
			}
		}
		return null;
	}

	public static String encriptaPass(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sha1;
		if (null == password) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.update(password.getBytes(), 0, password.length());
			sha1 = new BigInteger(1, digest.digest()).toString(16);
			return sha1;
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

}
