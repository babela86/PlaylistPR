package dei.uc.pt.ar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "lyric", uniqueConstraints = @UniqueConstraint(columnNames = {
		"music_id", "user_id" }))
public class Lyric {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 3000, name = "lyric")
	private String lyrics;

	@ManyToOne
	@JoinColumn(name="user_id")
	private Utilizador utilizador;

	@ManyToOne
	@JoinColumn(name="music_id")
	private Musica musica;

	public Lyric() {

	}

	public Lyric(String lyrics, Utilizador user, Musica music) {
		super();
		
		this.lyrics = lyrics;
		this.utilizador = user;
		this.musica = music;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Utilizador getUser() {
		return utilizador;
	}

	public void setUser(Utilizador user) {
		this.utilizador = user;
	}

	public Musica getMusic() {
		return musica;
	}

	public void setMusic(Musica music) {
		this.musica = music;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
