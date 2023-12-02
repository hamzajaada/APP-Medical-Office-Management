package Classes;

import java.io.Serializable;

// import java.io.Serializable;

public class RendezVous implements Serializable{
	
    private long id;
	private String jour;
	private long id_client;
	private long id_creneau;
	// Constructeur
	public RendezVous(long id,String d, long icl, long icr){
    this.setId(id);
    this.setJour(d);
		this.setId_client(icl);
		this.setId_creneau(icr);
	}
// Constructeur par default
   public RendezVous() {}
// Getters && Setters
    public void setId(long id) {this.id = id;}
    public long getId() {return id;}

	public long getId_client() {return id_client;}
	public void setId_client(long id_client) {this.id_client = id_client;}
	
	public String getJour() {return jour;}
	public void setJour(String jour) {this.jour = jour;}
	
	public long getId_creneau() {return id_creneau;}
	public void setId_creneau(long id_creneau) {this.id_creneau = id_creneau;}
}