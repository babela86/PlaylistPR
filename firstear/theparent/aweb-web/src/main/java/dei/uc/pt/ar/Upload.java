package dei.uc.pt.ar;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

@ManagedBean
@ApplicationScoped
public class Upload implements Serializable {

	private static final long serialVersionUID = 1L;

	private Part file;

	private String path;
	Properties props = System.getProperties();

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String upload(Part file) throws IOException {
		this.file = file;

		Random r = new Random();

		String name = "musica" + r.nextInt(1000);

		this.path = "/music/" + name + ".mp3";

		try {
			file.write(props.getProperty("user.dir") + "\\music\\" + name
					+ ".mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
