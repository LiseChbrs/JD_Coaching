package metier;
// Generated 8 f�vr. 2020 12:38:47 by Hibernate Tools 4.3.1



/**
 * ContenirSeanceId generated by hbm2java
 */
public class ContenirSeanceId  implements java.io.Serializable {


     private int idSeance;
     private int idProgramme;
     private int numOrdre;

    public ContenirSeanceId() {
    }

    public ContenirSeanceId(int idSeance, int idProgramme, int numOrdre) {
       this.idSeance = idSeance;
       this.idProgramme = idProgramme;
       this.numOrdre = numOrdre;
    }
   
    public int getIdSeance() {
        return this.idSeance;
    }
    
    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
    public int getIdProgramme() {
        return this.idProgramme;
    }
    
    public void setIdProgramme(int idProgramme) {
        this.idProgramme = idProgramme;
    }
    public int getNumOrdre() {
        return this.numOrdre;
    }
    
    public void setNumOrdre(int numOrdre) {
        this.numOrdre = numOrdre;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContenirSeanceId) ) return false;
		 ContenirSeanceId castOther = ( ContenirSeanceId ) other; 
         
		 return (this.getIdSeance()==castOther.getIdSeance())
 && (this.getIdProgramme()==castOther.getIdProgramme())
 && (this.getNumOrdre()==castOther.getNumOrdre());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdSeance();
         result = 37 * result + this.getIdProgramme();
         result = 37 * result + this.getNumOrdre();
         return result;
   }   


}


