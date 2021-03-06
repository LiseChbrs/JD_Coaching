package metier;
// Generated 8 fevr. 2020 16:33:01 by Hibernate Tools 4.3.1



/**
 * ContenirExercice generated by hbm2java
 */
public class ContenirExercice  implements java.io.Serializable {


     private ContenirExerciceId id;
     private Exercice exercice;
     private Seance seance;
     private Integer nbRepetitions;
     private Integer nbOccurenceRepetitions;
     private Float tempsPause;
     private Float duree;

    public ContenirExercice() {
    }

	
    public ContenirExercice(Exercice exercice, Seance seance, int numOrdre) {
        this.id = new ContenirExerciceId(exercice.getIdExercice(), seance.getIdSeance(), numOrdre);
        this.exercice = exercice;
        this.seance = seance;
    }
    public ContenirExercice(ContenirExerciceId id, Exercice exercice, Seance seance, int numOrdre, Integer nbRepetitions, Integer nbOccurenceRepetitions, Float tempsPause, Float duree) {
       this.id = new ContenirExerciceId(exercice.getIdExercice(), seance.getIdSeance(), numOrdre);
       this.exercice = exercice;
       this.seance = seance;
       this.nbRepetitions = nbRepetitions;
       this.nbOccurenceRepetitions = nbOccurenceRepetitions;
       this.tempsPause = tempsPause;
       this.duree = duree;
    }

    public ContenirExercice(Exercice exercice, Integer nbRepetitions, Integer nbOccurenceRepetitions, Float tempsPause, Float duree) {
        this.exercice = exercice;
        this.nbRepetitions = nbRepetitions;
        this.nbOccurenceRepetitions = nbOccurenceRepetitions;
        this.tempsPause = tempsPause;
        this.duree = duree;
    }
   
    public ContenirExerciceId getId() {
        return this.id;
    }
    
    public void setId(ContenirExerciceId id) {
        this.id = id;
    }
    public Exercice getExercice() {
        return this.exercice;
    }
    
    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }
    public Seance getSeance() {
        return this.seance;
    }
    
    public void setSeance(Seance seance) {
        this.seance = seance;
    }
    public Integer getNbRepetitions() {
        return this.nbRepetitions;
    }
    
    public void setNbRepetitions(Integer nbRepetitions) {
        this.nbRepetitions = nbRepetitions;
    }
    public Integer getNbOccurenceRepetitions() {
        return this.nbOccurenceRepetitions;
    }
    
    public void setNbOccurenceRepetitions(Integer nbOccurenceRepetitions) {
        this.nbOccurenceRepetitions = nbOccurenceRepetitions;
    }
    public Float getTempsPause() {
        return this.tempsPause;
    }
    
    public void setTempsPause(Float tempsPause) {
        this.tempsPause = tempsPause;
    }
    public Float getDuree() {
        return this.duree;
    }
    
    public void setDuree(Float duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "ContenirExercice{" + "id=" + id + ", exercice=" + exercice + ", seance=" + seance + ", nbRepetitions=" + nbRepetitions + ", nbOccurenceRepetitions=" + nbOccurenceRepetitions + ", tempsPause=" + tempsPause + ", duree=" + duree + '}';
    }


}


