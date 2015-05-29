package dei.uc.pt.ar;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "utilizador")
@NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u")
public class Utilizador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String name;
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nasc")
	private Date birthdate;

	// um utilizador pode inserir varias musicas
	@OneToMany(mappedBy = "utilizador")
	private List<Musica> musicas;

	// um utilizador pode ter varias playlists
	@OneToMany(mappedBy = "utilizador")
	private List<Playlist> playlists;

	public Utilizador() {
	}

	public Utilizador(String email, String name, String password, Date birthdate) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
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

	@Override
	public String toString() {
		return "Nome " + this.name;
	}

}
