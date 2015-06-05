package dei.uc.pt.ar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class RemoveFromPlaylist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MusicDAO md;

	@Inject
	private UserInput ui;

	private int idPlay;

	public String removeFrom(int idMus) {
		this.idPlay = ui.getIdPlay();
		boolean removed = md.deleteMusic(idPlay, idMus);
		if (removed == true) {
			FacesMessage msg = new FacesMessage("Music removed from playlist!",
					"INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "thePlaylist";
		} else {
			FacesMessage msg = new FacesMessage(
					"Problem removing music from playlist!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "thePlaylist";
		}
	}
}
