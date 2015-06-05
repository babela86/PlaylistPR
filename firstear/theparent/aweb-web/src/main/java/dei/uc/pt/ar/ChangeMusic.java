package dei.uc.pt.ar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class ChangeMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MusicDAO md;
	@Inject
	private UserInput ui;
	private String title;
	private String artist;
	private String year;
	private String album;
	private String path;

	public String deleteMusic(int idMusic){
		boolean deleted = md.deleteMusicFromDb(idMusic);
		if (deleted == true) {
			FacesMessage msg = new FacesMessage("Music deleted!",
					"INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Problem deleting music!",
					"ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "myMusics";
	}


	public String changeMusic(int idMusic) {
		Musica m = new Musica(this.title, this.artist, this.album, this.year, this.path, ui.getActiveUser());
		boolean changed = md.changeMusic(m, idMusic);
		if (changed==true){
			FacesMessage msg = new FacesMessage("Music info updated!", "INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "myMusics";
		} else {
			FacesMessage msg = new FacesMessage("Problem updating music!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "myMusics";
		}

	}

}
