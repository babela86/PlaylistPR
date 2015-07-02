package dei.uc.pt.ar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	@Inject
	private UserDAO ud;
	
	
	public void searchLyric(int musicID, String artist, String title){
		String lyric = cls.searchLyrics(artist, title);
		String msga ="";
		System.out.println("isto:"+lyric);
		if ((lyric != null) && (!lyric.equals(""))){
			msga = "Lyric added to DB (ChartLyricsSoap)!";
		} else {
			lyric = clr.searchLyrics(artist, title);
			if ((lyric != null) && (!lyric.equals(""))){
				msga = "Lyric added to DB (ChartLyricsRest)!";
			} else {
				lyric = lwr.searchLyrics(artist, title);
				if ((lyric != null) && (!lyric.equals("Not found"))){
					msga = "Lyric added to DB (LyricsWikiaRest)!";
				} else {
					msga = "Problem getting lyric!";
					lyric = null;
				}
			}	
		}
		
		if (lyric != null){
			try {
				ld.saveLyric(lyric, ud.findUserById(1), md.getMusic(musicID));
			} catch (Exception e){
				msga = "Lyric was already on DB!";
			}
		}
		
		FacesMessage msg = new FacesMessage(msga, "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public boolean renderButton(int musicID){
		ArrayList<Lyric> lyriclist= ld.findMyLyricByMusic(musicID);
			if (lyriclist.size()==0)
				return false;
			else
				return true;
	}
	
}
