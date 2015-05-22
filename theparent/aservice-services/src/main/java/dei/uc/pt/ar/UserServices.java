package dei.uc.pt.ar;

import javax.ejb.Stateless;

/**
 * @author jncor
 *
 */
@Stateless
public class UserServices {

	public String sayHello(){
		return "hello";
	}

}