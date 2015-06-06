package dei.uc.pt.ar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class AddToPlaylist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Render rd;
	@Inject
	private MusicDAO md;

	private int idPlay;

	public String addTo(int idPlay) {
		this.idPlay = idPlay;
		boolean added = md.addTo(idPlay, rd.getId());
		rd.hideTable();

		if (added == true) {
			FacesMessage msg = new FacesMessage("Music added to playlist!",
					"INFO MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);

			return "allMusics.xhtml?faces-redirect=true";
		} else {
			FacesMessage msg = new FacesMessage(
					"Problem adding music to playlist!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "allMusics.xhtml?faces-redirect=true";
		}
	}

	public int getIdPlay() {
		return idPlay;
	}

	public void setIdPlay(int idPlay) {
		this.idPlay = idPlay;
	}

}
