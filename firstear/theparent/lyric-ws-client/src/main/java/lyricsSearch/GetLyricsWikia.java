package lyricsSearch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LyricsResult")
public class GetLyricsWikia {

	@XmlElement(name = "lyrics")
	private String lyricMusic;

	// @XmlElement(name = "artist")
	// private String artistMusic;
	// @XmlElement(name = "song")
	// private String titleMusic;

	public String getLyric() {
		return lyricMusic;
	}

	public void setLyric(String lyric) {
		this.lyricMusic = lyric;
	}

	// public String getArtistMusic() {
	// return artistMusic;
	// }
	//
	// public void setArtistMusic(String artistMusic) {
	// this.artistMusic = artistMusic;
	// }
	//
	// public String getTitleMusic() {
	// return titleMusic;
	// }
	//
	// public void setTitleMusic(String titleMusic) {
	// this.titleMusic = titleMusic;
	// }

}
