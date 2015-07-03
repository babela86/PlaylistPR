package lyricsSearch;

import java.rmi.RemoteException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.chartlyrics.api.Apiv1SoapProxy;
import com.chartlyrics.api.GetLyricResult;

@Stateless
@LocalBean
public class ChartLyricsSoap {
	Apiv1SoapProxy soap = new Apiv1SoapProxy();
	String artist = null;
	String song = null;
	GetLyricResult result = null;
	boolean search = false;
	String lyricresult = null;

	public ChartLyricsSoap() {
		super();
	}

	public String searchLyrics(String artist, String song) {
		int count = 1;
		// Ir Buscar a lyric de uma musica por artista e titulo
		while (!search && count <= 5) {
			try {
				result = soap.searchLyricDirect(artist, song);
				lyricresult = result.getLyric();
				setLyricresult(result.getLyric());
				setArtist(result.getLyricArtist());
				setSong(result.getLyricSong());
				search = true;

			} catch (RemoteException e) {
				count++;

			}

		}
		return getLyricresult();
	}

	public String getLyricresult() {
		return lyricresult;
	}

	public void setLyricresult(String lyricresult) {
		this.lyricresult = lyricresult;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {

		this.artist = artist;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

}
