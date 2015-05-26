package dei.uc.pt.ar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "playlist_lnk")
@NamedQuery(name = "PlaylistLnk.findAll", query = "SELECT p FROM PlaylistLnk p")
public class PlaylistLnk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_playlist_lnk")
	private int idPlaylistLnk;

	@Column(name = "id_musica")
	private int idMusica;

	@Column(name = "id_playlist")
	private int idPlaylist;

	@OneToOne
	@JoinColumn(name = "id_playlist_lnk")
	private Musica musica;

	@OneToOne
	@JoinColumn(name = "id_playlist_lnk")
	private Playlist playlist;

	public PlaylistLnk() {
	}

	public PlaylistLnk(int idPlaylistLnk) {
		super();
		this.idPlaylistLnk = idPlaylistLnk;
	}

	public int getIdMusica() {
		return idMusica;
	}

	public void setIdMusica(int idMusica) {
		this.idMusica = idMusica;
	}

	public int getIdPlaylistLnk() {
		return idPlaylistLnk;
	}

	public void setIdPlaylistLnk(int idPlaylistLnk) {
		this.idPlaylistLnk = idPlaylistLnk;
	}

	public int getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

}
