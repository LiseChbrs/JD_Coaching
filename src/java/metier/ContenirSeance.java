package metier;
// Generated 8 f�vr. 2020 12:38:47 by Hibernate Tools 4.3.1



/**
 * ContenirSeance generated by hbm2java
 */
public class ContenirSeance  implements java.io.Serializable {


     private ContenirSeanceId id;
     private Programme programme;
     private Seance seance;

    public ContenirSeance() {
    }

    public ContenirSeance(ContenirSeanceId id, Programme programme, Seance seance) {
       this.id = id;
       this.programme = programme;
       this.seance = seance;
    }
   
    public ContenirSeanceId getId() {
        return this.id;
    }
    
    public void setId(ContenirSeanceId id) {
        this.id = id;
    }
    public Programme getProgramme() {
        return this.programme;
    }
    
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }
    public Seance getSeance() {
        return this.seance;
    }
    
    public void setSeance(Seance seance) {
        this.seance = seance;
    }




}


