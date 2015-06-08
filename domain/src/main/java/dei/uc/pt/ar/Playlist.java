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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPlaylist;
	@NotNull
	private String name;

	// uma playlist so pode pertencer a um utilizador

	@ManyToOne
	@JoinColumn(name = "id_util")
	private Utilizador utilizador;

	@ManyToMany
	private List<Musica> musicas;

	public Playlist() {
	}

	public int getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public Playlist(String name, Utilizador utilizador) {
		super();
		this.utilizador = utilizador;
		this.name = name;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMusica(Musica m) {
		if (this.musicas == null)
			this.musicas = new ArrayList<Musica>();
		musicas.add(m);
	}

	public void removeMusica(int idM) {
		for (Musica m : musicas) {
			if (m.getIdMusic() == idM) {
				musicas.remove(m);
			}
		}

	}

}
