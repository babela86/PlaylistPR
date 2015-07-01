package lyricsSearch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetLyricResult", namespace = "http://api.chartlyrics.com/")
public class GetLyricsChart {

	@XmlElement(name = "Lyric")
	private String lyricMusic;

	// @XmlElement(name = "LyricSong")
	// private String lyricSong;
	//
	// @XmlElement(name = "LyricArtist")
	// private String lyricArtist;

	public String getLyric() {
		return lyricMusic;
	}

	public void setLyric(String lyric) {
		this.lyricMusic = lyric;
	}

	// public String getSong() {
	// return lyricSong;
	// }
	//
	// public void setSong(String song) {
	// this.lyricSong = song;
	// }
	//
	// public String getLyricArtist() {
	// return lyricArtist;
	// }
	//
	// public void setLyricArtist(String lyricArtist) {
	// this.lyricArtist = lyricArtist;
	// }

}
