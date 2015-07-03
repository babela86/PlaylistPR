package lyricsSearch;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

@Named
@RequestScoped
public class ChartLyricsRest {

	String song = null;
	String artist = null;
	Response result = null;
	ResteasyWebTarget target = null;
	boolean search = false;
	String lyrics = null;
	String lyricresult = null;

	public ChartLyricsRest() {
		super();
	}

	public String searchLyrics(String artist, String song) {

		this.song = song;
		this.artist = artist;
		int count = 1;

		while (!search && count <= 5) {
			try {
				ResteasyClient client = new ResteasyClientBuilder().build();
				target = client
						.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="
								+ artist + "&song=" + song);
				result = target.request(MediaType.APPLICATION_XML).get();
				lyrics = result.readEntity(GetLyricsChart.class).getLyric();
				setLyricresult(lyrics);
				setLyricresult(lyrics);

				search = true;
			} catch (Exception e) {

				count++;
			}

		}
		return getLyricresult();

	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLyricresult() {
		return lyricresult;
	}

	public void setLyricresult(String lyricresult) {
		this.lyricresult = lyricresult;
	}

}
