package dei.uc.pt.ar;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class Render implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean tableShow = false;

	public boolean isTableShow() {
		return tableShow;
	}

	public void setTableShow(boolean tableShow) {
		this.tableShow = tableShow;
	}

	public void showTable(int id) {
		this.id = id;
		this.tableShow = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void hideTable() {

		this.tableShow = false;

	}

}
