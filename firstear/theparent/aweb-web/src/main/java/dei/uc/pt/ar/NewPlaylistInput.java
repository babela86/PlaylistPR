package dei.uc.pt.ar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class NewPlaylistInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlaylistDAO pd;
	@Inject
	private UserInput ui;

	private String name;

	public NewPlaylistInput() {

	}

	public String newPlaylist() {
		Playlist p = new Playlist(this.name, ui.getActiveUser());
		boolean added = pd.newPlaylist(p);
		if (added == true) {
			FacesMessage msg = new FacesMessage("Playlist added to DB!",
					"INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Playlist already exists!",
					"ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "addPlaylist";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
