/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author ptitp
 */
public class SessionClassement {

    
    private Exercice exercice;
  
    private Integer nbRepetitions;
    private Integer nbOccurenceRepetitions;
    private Float tempsPause;
    private Float duree;

    public SessionClassement(Exercice exercice, Integer nbRepetitions, Integer nbOccurenceRepetitions, Float tempsPause, Float duree) {
        this.exercice = exercice;
        this.nbRepetitions = nbRepetitions;
        this.nbOccurenceRepetitions = nbOccurenceRepetitions;
        this.tempsPause = tempsPause;
        this.duree = duree;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public Integer getNbRepetitions() {
        return nbRepetitions;
    }

    public void setNbRepetitions(Integer nbRepetitions) {
        this.nbRepetitions = nbRepetitions;
    }

    public Integer getNbOccurenceRepetitions() {
        return nbOccurenceRepetitions;
    }

    public void setNbOccurenceRepetitions(Integer nbOccurenceRepetitions) {
        this.nbOccurenceRepetitions = nbOccurenceRepetitions;
    }

    public Float getTempsPause() {
        return tempsPause;
    }

    public void setTempsPause(Float tempsPause) {
        this.tempsPause = tempsPause;
    }

    public Float getDuree() {
        return duree;
    }

    public void setDuree(Float duree) {
        this.duree = duree;
    }

}
