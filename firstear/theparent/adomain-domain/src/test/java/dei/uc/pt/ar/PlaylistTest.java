package dei.uc.pt.ar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistTest {

	@InjectMocks
	Playlist pd;

	@Test
	public void testGetId() {
		System.out.println("getId");
		Playlist play = new Playlist();
		int expected = 0;
		int result = play.getIdPlaylist();
		assertEquals(expected, result);

	}
}
