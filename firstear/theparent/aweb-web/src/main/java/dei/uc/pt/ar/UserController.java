package dei.uc.pt.ar;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class UserController implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserController(){}
	
	@Inject
	private UserRegister ur;
	
	public String getActiveUser(){
		return ur.getActiveUSer().getName();
	}
	
}
