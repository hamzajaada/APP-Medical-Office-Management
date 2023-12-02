package Classes;

import java.io.Serializable;

public class Client implements Serializable {
    private long Id;
    private int Version;
	private String Titre;
	private String Nom;
	private String Prenom;
    // Constructeur :
    public Client(long id,int v, String t, String n, String p){
               this.Id = id;
               this.Version = v;
               this.Titre = t;
               this.Nom = n;
               this.Prenom =p;
        }
    // Constructeur par default :
    public Client(){

    }
    // GET && SET (Id)
    public void setId(long id) {this.Id = id;}
    public long getId() {return Id;}
     // GET && SET (Version)
     public int getVersion() {return Version;}
     public void setVersion(int version) {this.Version = version;}
      // GET && SET (Titre)
      public String getTitre() {return Titre;}
	  public void setTitre(String titre) {this.Titre = titre;}
      // GET && SET (Nom)
      public String getNom() {return Nom;}
	  public void setNom(String nom) {this.Nom = nom;}
      // GET && SET (prenom)
      public String getPrenom() {return Prenom;}
	  public void setPrenom(String prenom) {this.Prenom = prenom;}

}
