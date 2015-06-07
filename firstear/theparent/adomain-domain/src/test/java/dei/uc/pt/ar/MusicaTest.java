package dei.uc.pt.ar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MusicaTest {

	@InjectMocks
	Musica md;

	@Test
	public void testGetId() {
		System.out.println("getId");
		Musica mustest = new Musica();
		int expected = 0;
		int result = mustest.getIdMusic();
		assertEquals(expected, result);

	}

}
