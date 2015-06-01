package dei.uc.pt.ar;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class UserInput implements Serializable{


	private static final long serialVersionUID = 1L;

	@Inject
	private UserRegister ur;
	private HttpSession session;

	private String email;
	private String pass;
	private String name;
	private String year;
	private String month;
	private String day;
	private Date birthdate;
	
	private Utilizador activeUser;
	private boolean userLoged=true;

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	public UserInput() {
	}

	public String newUser() throws ParseException {
		this.birthdate = ft
				.parse(this.year + "-" + this.month + "-" + this.day);
		
		Utilizador u = new Utilizador(this.email, this.name, this.pass, this.birthdate);


		FacesMessage msg = new FacesMessage(ur.newUser(u), "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		this.day = "";
		this.year = "";
		this.month = "";
		this.pass = "";
		this.email = "";
		this.name ="";
		return "login";
	}

	public String loginUser() throws ParseException {
		if (ur.loginUser(email, pass)==null){
			FacesMessage msg = new FacesMessage("Login incorrecto!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login";
		} else {
			this.activeUser=ur.loginUser(email, pass);
			this.name=activeUser.getName();
			ur.populate();
			startSession();
			return "resources/Authorized/myPlaylist.xhtml?faces-redirect=true";
		}
	}
	
	public String logoutUser() {
		
		this.activeUser=null;
		this.day = "";
		this.year = "";
		this.month = "";
		this.pass = "";
		this.email = "";
		this.name ="";
		if(FacesContext.getCurrentInstance()!=null)
			FacesContext.getCurrentInstance().getExternalContext()
					.invalidateSession();
		return "login";
	}
	
	public Utilizador getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Utilizador activeUser) {
		this.activeUser = activeUser;
	}

	public boolean isUserLoged() {
		return userLoged;
	}

	public void setUserLoged(boolean userLoged) {
		this.userLoged = userLoged;
	}

	public String getEmail() {
		return email;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}
	
	public void startSession() {
		this.session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.session.setAttribute("userLoged", true);
	}

	public void endSession() {
		if (this.session != null)
			this.session.invalidate();
		startSession();
		this.userLoged = false;
	}

}
