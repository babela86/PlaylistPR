package dei.uc.pt.ar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class Populate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "Playlist")
	private EntityManager em;

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	public Populate() {

	}

	public String populando() throws ParseException, NoSuchAlgorithmException,
			UnsupportedEncodingException {

		Utilizador[] users = {
				new Utilizador("pedro@gmail.com", "pedro",
						UserRegister.encriptaPass("123"),
						ft.parse("1983-01-01")),
						new Utilizador("rafa@gmail.com", "rafa",
						UserRegister.encriptaPass("123"),
								ft.parse("1986-04-30")) };

		Playlist[] plays = { new Playlist("rafa's playlist"),
				new Playlist("p's playlist") };

		Musica[] musics = {
				new Musica("Lean on", "Major Lazer & DJ Snake",
						"Peace is the Mission", "2015",
						"https://www.youtube.com/watch?v=YqeW9_5kURI"),
						new Musica("O corpo é que paga", "António Variações",
								"O melhor de António Variações", "1997", "qqcoisa.com"),
								new Musica("Era só Jajão", "Master Jake", "nao sei", "2014",
										"https://www.youtube.com/watch?v=vMgp904a3nc"),
										new Musica("Não me Toca", "Anselmo Ralph", "A dor de Cupido",
												"2011",
												"https://www.youtube.com/watch?v=whH2oDg28-k&list=RDwhH2oDg28-k"),
												new Musica("YMCA", "Village People", "Cruisn", "1978",
														"https://www.youtube.com/watch?v=CS9OO0S5w2k"),
														new Musica("I wanna Dance with Somebody (Who Loves Me)",
																"Whitney Houston", "Whitney", "1987",
																"https://www.youtube.com/watch?v=eH3giaIzONA"),

																new Musica("Show das Poderosas", "Anitta", "Nao sei", "2013",
																		"https://www.youtube.com/watch?v=FGViL3CYRwg"),

																		new Musica("No woman no cry", "Bob Marley", "Natty Dread",
																				"1974", "https://www.youtube.com/watch?v=jGqrvn3q1oo") };

		/*
		 * users[0].addPlaylist(plays[1]); users[1].addPlaylist(plays[0]);
		 * 
		 * plays[0].addMusica(musics[1]); plays[0].addMusica(musics[0]);
		 * plays[1].addMusica(musics[3]); plays[1].addMusica(musics[2]);
		 * plays[1].addMusica(musics[0]); plays[0].addMusica(musics[4]);
		 * plays[0].addMusica(musics[5]); plays[1].addMusica(musics[6]);
		 * plays[0].addMusica(musics[7]);
		 * 
		 * users[1].addMusica(musics[1]); users[0].addMusica(musics[0]);
		 * users[1].addMusica(musics[3]); users[1].addMusica(musics[2]);
		 * users[0].addMusica(musics[0]); users[1].addMusica(musics[4]);
		 * users[0].addMusica(musics[5]); users[1].addMusica(musics[6]);
		 * users[0].addMusica(musics[7]);
		 */

		for (Utilizador u : users)
			em.persist(u);

		for (Playlist p : plays)
			em.persist(p);

		for (Musica m : musics)
			em.persist(m);

		return "login";
	}

}