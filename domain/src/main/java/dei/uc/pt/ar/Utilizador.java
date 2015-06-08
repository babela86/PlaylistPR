package dei.uc.pt.ar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "utilizador")
@NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u")
public class Utilizador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUtilizador;
	@NotNull
	@Column(unique=true)
	private String email;
	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasc")
	private Date birthdate;

	// um utilizador pode inserir varias musicas

	@OneToMany(mappedBy = "utilizador", fetch = FetchType.EAGER)
	private List<Musica> musicas = new ArrayList<Musica>();

	// um utilizador pode ter varias playlists

	@OneToMany(mappedBy = "utilizador", fetch = FetchType.EAGER)
	private List<Playlist> playlists = new ArrayList<Playlist>();

	public Utilizador() {
	}

	public Utilizador(String email, String name, String password, Date birthdate) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
	}

	public int getIdUtilizador() {
		return idUtilizador;
	}

	public void setIdUtilizador(int idUtilizador) {
		this.idUtilizador = idUtilizador;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void addMusica(Musica e) {
		if (this.musicas == null)
			this.musicas = new ArrayList<>();
		musicas.add(e);
	}

	public void addPlaylist(Playlist p) {
		if (this.playlists == null)
			this.playlists = new ArrayList<>();
		playlists.add(p);
	}

	@Override
	public String toString() {
		return "Nome " + this.name;
	}

}
