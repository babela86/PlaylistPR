package dei.uc.pt.ar;

import java.io.Serializable;

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


	public void setChanges() {}
		
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
	
}
