package dei.uc.pt.ar;


import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usercollection")
@ApplicationScoped
public class LogedUsers {
	
	public LogedUsers() {
	}

	private ArrayList<Utilizador> listalogados;

	public ArrayList<Utilizador> getListalogados() {
		return listalogados;
	}

	public void setListalogados(ArrayList<Utilizador> listalogados) {
		this.listalogados = listalogados;
	}
	
	public void add(Utilizador u){
		if (this.listalogados == null)
			this.listalogados = new ArrayList<>();
		listalogados.add(u);
	}
	
	public void remove(Utilizador u){
		listalogados.remove(u);
	}
	
	
	
	
	
}
