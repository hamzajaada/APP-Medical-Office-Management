package Classes;

import java.io.Serializable;

import java.io.Serializable;

public class Creneau implements Serializable{
	// private static final long serialVersionUID = 2L;
	
	private long id;
	private int version;
	private int hdebut;
	private int mdebut;
	private int hfin;
	private int mfin;
	private long id_medecin;
	// Contructeur
	public Creneau(long id,int v, int ho, int md ,int hf, int mf, long idm){
		this.setId(id);
		this.setVersion(v);
		this.setHdebut(ho);
		this.setMdebut(md);
		this.setHfin(hf);
		this.setMfin(mf);
		this.setId_medecin(idm);
	}
    // Constructeur par default
	public Creneau() {}
    // Getters && Setters
	public void setId(long id) {this.id = id;}
	public long getId() {return id;}

	public int getVersion() {return version;}
	public void setVersion(int version) {this.version = version;}

	public int getHdebut() {return hdebut;}
	public void setHdebut(int hdebut) {this.hdebut = hdebut;}

	public int getMdebut() {return mdebut;}
	public void setMdebut(int mdebut) {this.mdebut = mdebut;}

	public int getHfin() {return hfin;}
	public void setHfin(int hfin) {this.hfin = hfin;}

	public int getMfin() {return mfin;}
	public void setMfin(int mfin) {this.mfin = mfin;}

	public long getId_medecin() {return id_medecin;}
	public void setId_medecin(long id_medecin) {this.id_medecin = id_medecin;}
}
