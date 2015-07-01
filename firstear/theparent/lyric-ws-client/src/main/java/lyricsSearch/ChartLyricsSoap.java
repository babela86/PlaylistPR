package lyricsSearch;

import java.rmi.RemoteException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.chartlyrics.api.Apiv1SoapProxy;
import com.chartlyrics.api.GetLyricResult;

@Stateless
@LocalBean
public class ChartLyricsSoap {
	// public static void main(String[] args) {
	Apiv1SoapProxy soap = new Apiv1SoapProxy();
	String artist = null;
	String song = null;
	// String artist = "suzanne vega";
	// String song = "luka";
	GetLyricResult result = null;
	boolean search = false;
	String lyricresult = null;

	public ChartLyricsSoap() {
		super();
	}

	public String searchLyrics(String artist, String song) {
		// this.artist = artist;
		// this.song = song;
		int count = 1;
		// Ir Buscar a lyric de uma musica por artista e titulo

		while (!search && count <= 5) {
			try {
				result = soap.searchLyricDirect(artist, song);
				lyricresult = result.getLyric();
				setLyricresult(result.getLyric());
				setArtist(result.getLyricArtist());
				setSong(result.getLyricSong());
				System.out.println(artist);
				search = true;

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Connecting chartLyrics by soap...");
				count++;

			}

		}
		return getLyricresult();
		// System.out.println("Artist: " + result.getLyricArtist());
		// System.out.println("Song: " + result.getLyricSong());
		//
		// System.out.println("Lyric: \n" + result.getLyric());

		// return lyricresult;

	}

	public String getLyricresult() {
		// System.out.println(lyricresult);
		return lyricresult;
	}

	public void setLyricresult(String lyricresult) {
		// System.out.println(lyricresult);

		this.lyricresult = lyricresult;
	}

	public String getArtist() {
		System.out.println(artist);
		return artist;
	}

	public void setArtist(String artist) {

		this.artist = artist;
	}

	public String getSong() {
		System.out.println(song);
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

}
