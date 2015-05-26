package dei.uc.pt.ar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author jncor
 *
 */
@Stateless
public class UserServices {
	@PersistenceContext(name = "Playlist")
	EntityManager em;

	public String sayHello() {
		return "hello";
	}

}