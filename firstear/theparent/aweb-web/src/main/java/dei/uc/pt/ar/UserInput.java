package dei.uc.pt.ar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class UserInput {

	@Inject
	private UserRegister ur;

	private String email;
	private String pass;
	private String name;
	private String year;
	private String month;
	private String day;
	private Date birthdate;

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	public UserInput() {
	}

	public String newUser() throws ParseException {
		this.birthdate = ft
				.parse(this.year + "-" + this.month + "-" + this.day);

		FacesMessage msg = new FacesMessage(ur.newUser(this.email, this.name,
				this.pass, this.birthdate), "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		this.day = "";
		this.year = "";
		this.month = "";
		this.name = "";
		this.pass = "";
		this.email = "";
		return "login";
	}

	public String loginUser() throws ParseException {
		if (!ur.loginUser(email, pass)){
			FacesMessage msg = new FacesMessage("Login incorrecto!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login";
		} else {
		return "myPlaylist?faces-redirect=true";
		}
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

}