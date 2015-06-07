package dei.uc.pt.ar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class UserInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private String result = "";

	@Inject
	private UserRegister ur;
	@Inject
	private MusicDAO md;
	@Inject
	private PlaylistDAO pd;

	private String email;
	private String pass;
	private String name;
	private ArrayList<Musica> search = new ArrayList<Musica>();
	private int idPlay;

	private String year;
	private String month;
	private String day;
	private Date birthdate;
	private String artist;
	private String title;
	private HttpSession session;
	private ArrayList<Musica> allmusics = new ArrayList<Musica>();

	private Utilizador activeUser;
	private boolean userLoged = true;

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	public UserInput() {
	}

	public String newUser() throws ParseException, NoSuchAlgorithmException,
	UnsupportedEncodingException {
		if (Validator.dateValidator(this.day, this.month, this.year)) {
			this.birthdate = ft.parse(this.year + "-" + this.month + "-"
					+ this.day);
			Utilizador u = new Utilizador(this.email, this.name, this.pass,
					this.birthdate);
			FacesMessage msg = new FacesMessage(ur.newUser(u), "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			this.day = "";
			this.year = "";
			this.month = "";
			this.pass = "";
			this.email = "";
			this.name = "";
			return "login";
		} else {
			FacesMessage msg = new FacesMessage("Invalid date!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login";
		}
	}

	public String loginUser() throws ParseException, NoSuchAlgorithmException,
	UnsupportedEncodingException {
		Utilizador util = ur.loginUser(this.email, this.pass);
		if (util == null) {
			FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if (FacesContext.getCurrentInstance() != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
			return "login";
		} else {
			this.activeUser = util;
			this.name = activeUser.getName();
			startSession();
			allmusics = listallmusics();
			return "resources/Authorized/myPlaylist.xhtml?faces-redirect=true";
		}
	}

	public String logoutUser() {
		this.activeUser = null;
		this.day = "";
		this.year = "";
		this.month = "";
		this.pass = "";
		this.email = "";
		this.name = "";
		// endSession();
		if (FacesContext.getCurrentInstance() != null)
			FacesContext.getCurrentInstance().getExternalContext()
			.invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	public ArrayList<Musica> listallmusics() {
		return md.findAllMusic();
	}
	
	public void MusicByTitleDesc() {
		this.allmusics = md.MusicByTitleDesc();
	}
	
	public void MusicByTitleAsc() {
		this.allmusics = md.MusicByTitleAsc();
	}
	
	public void MusicByArtistDesc() {
		this.allmusics = md.MusicByArtistDesc();
	}
	
	public void MusicByArtistAsc() {
		this.allmusics = md.MusicByArtistAsc();
	}
	
	public void MusicByAlbumAsc() {
		this.allmusics = md.MusicByAlbumAsc();
	}
	
	public void MusicByAlbumDesc() {
		this.allmusics = md.MusicByAlbumDesc();
	}
	
	public void MusicByYearAsc() {
		this.allmusics = md.MusicByYearAsc();
	}
	
	public void MusicByYearDesc() {
		this.allmusics = md.MusicByYearDesc();
	}

	public ArrayList<Musica> listmymusics() {
		return md.findMyMusic(activeUser.getIdUtilizador());
	}

	public ArrayList<Playlist> listmyplaylists() {
		return pd.findMyPlaylists(activeUser.getIdUtilizador());
	}

	public String searchMusics() {
		if (this.title.equals("")) {
			search = md.findArtistMusic(this.artist);
		} else if (this.artist.equals("")) {
			search = md.findTitleMusic(this.title);
		} else {
			search = md.findArtistTitleMusic(this.title, this.artist);
		}
		return "searchMusic";
	}

	public ArrayList<Musica> getSearch() {
		return search;
	}

	public void setSearch(ArrayList<Musica> search) {
		this.search = search;
	}

	public Utilizador getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Utilizador activeUser) {
		this.activeUser = activeUser;
	}

	public boolean isUserLoged() {
		return userLoged;
	}

	public void setUserLoged(boolean userLoged) {
		this.userLoged = userLoged;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void startSession() {
		session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("userLoged", true);
	}

	public void endSession() {
		if (session != null)
			session.invalidate();
		userLoged = false;
	}

	public String showPlaylist(int idPlay) {
		this.idPlay = idPlay;
		return "thePlaylist.xhtml?redirect=true";
	}

	public ArrayList<Musica> musicsofplaylist() {

		return md.listMusicasPlaylist(idPlay);
	}

	public int getIdPlay() {
		return idPlay;
	}

	public void setIdPlay(int idPlay) {
		this.idPlay = idPlay;
	}

	public ArrayList<Musica> getAllmusics() {
		return allmusics;
	}

	public void setAllmusics(ArrayList<Musica> allmusics) {
		this.allmusics = allmusics;
	}

	public String getResult() {
		return result;
	}
}
