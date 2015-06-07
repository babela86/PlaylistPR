package dei.uc.pt.ar;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserInputTest {

	@Mock
	UserRegister ur;
	@Mock
	MusicDAO md;
	@Mock
	PlaylistDAO pd;

	@InjectMocks
	UserInput ui;

	Utilizador u;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-aa");

	// @SuppressWarnings("static-access")
	// @Before
	// public void setUP() throws NoSuchAlgorithmException,
	// UnsupportedEncodingException, ParseException {
	// u = new Utilizador("rafa@gmail.com", "rafa", ur.encriptaPass("123"),
	// df.parse("1986-04-30"));
	// }
	@Ignore
	@Test
	public void existentUser() throws NoSuchAlgorithmException,
			UnsupportedEncodingException, ParseException {
		ui.setDay("30");
		ui.setMonth("04");
		ui.setYear("1986");
		ui.setEmail("rafa@gmail.com");
		ui.setName("rafa");
		ui.setPass("123");

		ui.newUser();
		String result = ui.getResult();
		assertEquals("", result);
	}
	// public String newUser() throws ParseException, NoSuchAlgorithmException,
	// UnsupportedEncodingException {
	// if (Validator.dateValidator(this.day, this.month, this.year)) {
	// this.birthdate = ft.parse(this.year + "-" + this.month + "-"
	// + this.day);
	// Utilizador u = new Utilizador(this.email, this.name, this.pass,
	// this.birthdate);
	// FacesMessage msg = new FacesMessage(ur.newUser(u), "ERROR MSG");
	// msg.setSeverity(FacesMessage.SEVERITY_INFO);
	// if (FacesContext.getCurrentInstance() != null)
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// this.day = "";
	// this.year = "";
	// this.month = "";
	// this.pass = "";
	// this.email = "";
	// this.name = "";
	// return "login";
	// } else {
	// FacesMessage msg = new FacesMessage("Invalid date!", "ERROR MSG");
	// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	// if (FacesContext.getCurrentInstance() != null)
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// return "login";
	// }
	// }
}
