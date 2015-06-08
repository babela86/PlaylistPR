package dei.uc.pt.ar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	@InjectMocks
	Utilizador ud;

	@Test
	public void testGetId() {
		System.out.println("getId");
		Utilizador us = new Utilizador();
		int expected = 0;
		int result = us.getIdUtilizador();
		assertEquals(expected, result);

	}

}
