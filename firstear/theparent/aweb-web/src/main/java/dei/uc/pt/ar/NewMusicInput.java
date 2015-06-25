package dei.uc.pt.ar;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@SessionScoped
@Named
public class NewMusicInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MusicDAO md;
	@Inject
	private UserInput ui;
	@Inject
	private Upload up;

	private String title;
	private String artist;
	private String album;
	private String year;
	private String path;
	private Part file;

	public NewMusicInput() {

	}

	public String newMusic() throws IOException {

		this.path = up.upload(file);
		Musica m = new Musica(this.title, this.artist, this.album, this.year,
				this.path, ui.getActiveUser());
		boolean added = md.newMusic(m);
		if (added == true) {
			ui.setAllmusics(ui.listallmusics());
			FacesMessage msg = new FacesMessage("Music added to DB!",
					"INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {

			FacesMessage msg = new FacesMessage("Music already exists!",
					"ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "addMusics";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
