package dei.uc.pt.ar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "musica")
@NamedQuery(name = "Musica.findAll", query = "SELECT m FROM Musica m")
public class Musica implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMusic;
	@NotNull
	private String title;
	@NotNull
	private String artist;
	@NotNull
	private String album;
	@NotNull
	private String year;
	@NotNull
	private String path;

	// um utilizador pode adicionar varias musicas

	@ManyToOne
	@JoinColumn(name = "id_util")
	private Utilizador utilizador;

	@ManyToMany(mappedBy = "musicas")
	private List<Playlist> playlist = new ArrayList<Playlist>();

	public Musica() {
	}

	public Musica(String title, String artist, String album, String year,
			String path, Utilizador utilizador) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.path = path;
		this.utilizador = utilizador;
	}

	public List<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
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

	public int getIdMusic() {
		return idMusic;
	}

	public void setIdMusic(int idMusic) {
		this.idMusic = idMusic;
	}

}
