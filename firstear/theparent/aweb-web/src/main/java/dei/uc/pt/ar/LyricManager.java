package dei.uc.pt.ar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lyricsSearch.ChartLyricsRest;
import lyricsSearch.ChartLyricsSoap;
import lyricsSearch.LyricsWikiaRest;

@SessionScoped
@Named
public class LyricManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1782745096868271725L;

	@Inject
	private ChartLyricsRest clr;
	@Inject
	private ChartLyricsSoap cls;
	@Inject
	private LyricsWikiaRest lwr;
	@Inject
	private UserInput ui;
	@Inject 
	private LyricDAO ld;
	@Inject
	private MusicDAO md;
	
	
	public void searchLyric(int musicID, String artist, String title){
		String lyric = clr.searchLyrics(artist, title);
		boolean found = false;
		if (!lyric.equals("")){
			ld.saveLyric(lyric, ui.getActiveUser(), md.getMusic(musicID));
			found = true;
		}
		
		
		
	}
	
}
