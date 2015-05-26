package dei.uc.pt.ar;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "Musica.findAll", query = "SELECT m FROM Musica m")
public class Musica implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String artist;
	private String album;
	private String year;
	private String path;

	// um utilizador pode adicionar varias musicas
	@ManyToOne
	@JoinColumn(name = "id_util")
	private Utilizador utilizador;

	@OneToOne(mappedBy = "musica")
	private PlaylistLnk playlistLnk;

	public Musica() {
	}

	public Musica(String title, String artist, String album, String year,
			String path) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.path = path;
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

}
