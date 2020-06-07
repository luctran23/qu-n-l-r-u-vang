package nhom20;

import java.io.Serializable;
import java.util.Comparator;

public class Ruou implements Serializable {
	private String maRuou, tenRuou,cbgroup, dungTich, nongDo, xuatXu, tinhTrang, kieuCach;

    

	public String getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	public String getMaRuou() {
		return maRuou;
	}

	public void setMaRuou(String maRuou) {
		this.maRuou = maRuou;
	}
        
	public String getTenRuou() {
		return tenRuou;
	}

	public void setGroup(String group) {
		this.tenRuou = group;
	}

	public String getDungTich() {
		return dungTich;
	}

	public void setDungTich(String dungTich) {
		this.dungTich = dungTich;
	}

	public String getNongDo() {
		return nongDo;
	}

	public void setNongDo(String nongDo) {
		this.nongDo = nongDo;
	}

        public String getTinhTrang() {
            return tinhTrang;
        }

        public void setTinhTrang(String tinhTrang) {
            this.tinhTrang = tinhTrang;
        }

        public String getKieuCach() {
            return kieuCach;
        }

        public void setKieuCach(String kieuCach) {
            this.kieuCach = kieuCach;
        }
        
	public Ruou() {
	}

	public Ruou(String maRuou, String tenRuou, String group, String dungTich,String nongDo, String xuatXu, String tinhTrang, String kieuCach) {
		super();
		this.maRuou = maRuou;
		this.tenRuou = tenRuou;
                this.cbgroup = group;
		this.dungTich = dungTich;
		this.nongDo = nongDo;
		this.xuatXu = xuatXu;
                this.tinhTrang = tinhTrang;
                this.kieuCach = kieuCach;
		System.out.println("Xuat xu: " + xuatXu);
	}

	private String getLastNameOfName() {
		String str = getMaRuou();
		int i = str.length() - 1;
		while (i > 0) {
			if (str.charAt(i) == ' ') {
				return str.substring(i + 1);
			}
			i--;
		}
		return str;
	}

	public String toString() {
		return maRuou + ", " + nongDo + ", " + tenRuou + ", " + dungTich;
	}

	public static Comparator<Ruou> PersonNameComparator = new Comparator<Ruou>() {
		public int compare(Ruou p1, Ruou p2) {
			return p1.getXuatXu().compareTo(p2.getXuatXu());
		}
	};
}
