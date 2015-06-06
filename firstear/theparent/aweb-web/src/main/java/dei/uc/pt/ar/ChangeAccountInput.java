package dei.uc.pt.ar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class ChangeAccountInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserDAO ud;
	@Inject
	private UserInput ui;
	
	
	private String email;
	private String pass;
	private String name;
	private String year;
	private String month;
	private String day;
	private Date birthdate;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	
	public ChangeAccountInput(){}
	
	
	public String changeAccount() throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException {
		if (Validator.dateValidator(this.day, this.month , this.year)){
	
			birthdate= ft
					.parse(this.year + "-" + this.month + "-" + this.day);
			
			Utilizador u = new Utilizador(this.email, this.name, UserRegister.encriptaPass(this.pass),this.birthdate);
			boolean changed = ud.changeAccount(u, ui.getActiveUser());
			if (changed==true){
				FacesMessage msg = new FacesMessage("User account updated!", "INFO MSG");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				if (FacesContext.getCurrentInstance() != null)
					FacesContext.getCurrentInstance().addMessage(null, msg);
				ui.setActiveUser(null);
				ui.setUserLoged(false);
				FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
				return "/login.xhtml?faces-redirect=true";
			} else {
				FacesMessage msg = new FacesMessage("Problem updating account!", "ERROR MSG");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				if (FacesContext.getCurrentInstance() != null)
					FacesContext.getCurrentInstance().addMessage(null, msg);
				return "changeAccount";
			}
		}else {
			FacesMessage msg = new FacesMessage("Invalid date!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "changeAccount";
		}
	
	}
	
	public String deleteAccount() {	
		boolean apagada = ud.deleteAccount(ui.getActiveUser());
		if (apagada==true){
			ui.logoutUser();
			return "/login.xhtml?faces-redirect=true";
		}else{
			FacesMessage msg = new FacesMessage("Problem deleting account!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "changeAccount";
		}
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}
}
