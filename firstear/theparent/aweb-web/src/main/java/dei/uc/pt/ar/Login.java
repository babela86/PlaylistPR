package dei.uc.pt.ar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@SessionScoped
@Named
public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 247547538405625954L;

	@Inject
	private UserInput ui;
	
	private String email;
	private String password;

	public String login() throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try{
			request.login(email, password);
			ui.setEmail(email);
			ui.setPass(password);
			ui.loginUser();
		} catch (ServletException e){
			return "/loginerror.xhtml";
		}
		return "/resources/Authorized/myPlaylist.xhtml?faces-redirect=true";
	}

	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try{
			ui.logoutUser();
			request.logout();
			return "/login.xhtml?faces-redirect=true";
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
			return "/resources/Authorized/myPlaylist.xhtml?faces-redirect=true";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
