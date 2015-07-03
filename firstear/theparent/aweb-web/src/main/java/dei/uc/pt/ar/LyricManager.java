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
	
	private int activeMusic=0;

	private boolean onOff=false;
	
	private String lyric=""; 

	public void searchLyric(int musicID, String artist, String title){
		String lyric = cls.searchLyrics(artist, title);
		String msga ="";
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
	
	public int getActiveMusic() {
		return activeMusic;
	}

	public void setActiveMusic(int activeMusic) {
		this.activeMusic = activeMusic;
	}
	
	public void renderOFF(){
		setOnOff(false);
	}
	
	public void getTheLyric(int musicID){
		this.activeMusic=musicID;
		//metodo para ir buscar a lyric e dps mete o render a true
		Lyric lyricresult = ld.searchLyric(ui.getActiveUser().getIdUtilizador(), musicID);
		if (lyricresult!=null){
			this.lyric = lyricresult.getLyrics();
			setOnOff(true);
		} else {
			lyricresult = ld.searchLyric(1, musicID);
			this.lyric = lyricresult.getLyrics();
			setOnOff(true);
		}
	}
	
	public boolean isOnOff() {
		return onOff;
	}

	public void setOnOff(boolean onOff) {
		this.onOff = onOff;
	}
	
	public void saveLyric(int musicID){
		
		
		//grava lyric na BD
		try{
			ld.saveLyric(this.lyric, ui.getActiveUser(), md.getMusic(this.activeMusic));
		} catch (Exception e) {
			ld.updateLyric(this.lyric, ui.getActiveUser(), md.getMusic(this.activeMusic));
		}
	}
	
	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
}
