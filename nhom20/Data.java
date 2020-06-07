package nhom20;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
	private ArrayList<Ruou> listRuou = new ArrayList<Ruou>();
	private ArrayList<String> listGroup;

	public Data() {
	}

	public ArrayList<Ruou> getListRuou() {
		return listRuou;
	}

	public void setListRuou(ArrayList<Ruou> listRuou) {
		this.listRuou = listRuou;
	}

	public ArrayList<String> getListGroup() {
		listGroup = new ArrayList<String>();
		for (int i = 0; i < listRuou.size(); i++) {
			boolean check = true;
			for (int j = i - 1; j >= 0; j--) {
				if (listRuou.get(i).getTenRuou()
						.equals(listRuou.get(j).getTenRuou())) {
					check = false;
					break;
				}
			}
			if (check) {
				listGroup.add(listRuou.get(i).getTenRuou());
			}
		}
		return listGroup;
	}

	public void setListGroup(ArrayList<String> listGroup) {
		this.listGroup = listGroup;
	}
}
