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
	@Mock
	Populate pp;

	Utilizador u;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-aa");

	@Ignore
	@Test
	public void existentUserTest() throws NoSuchAlgorithmException,
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

}
