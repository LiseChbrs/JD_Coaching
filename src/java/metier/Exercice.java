package metier;
// Generated 8 fevr. 2020 16:33:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Exercice generated by hbm2java
 */
public class Exercice  implements java.io.Serializable {


     private Integer idExercice;
     private String nomExercice;
     private String descriptionExercice;
     private String imageExercice;
     private String videoExercice;
     private Set objectifExercices = new HashSet(0);
     private Set categorieExercices = new HashSet(0);
     private Set contenirExercices = new HashSet(0);

    public Exercice() {
    }

	
    public Exercice(String nomExercice, String descriptionExercice, String imageExercice) {
        this.nomExercice = nomExercice;
        this.descriptionExercice = descriptionExercice;
        this.imageExercice = imageExercice;
    }
    public Exercice(String nomExercice, String descriptionExercice, String imageExercice, String videoExercice, Set objectifExercices, Set categorieExercices, Set contenirExercices) {
       this.nomExercice = nomExercice;
       this.descriptionExercice = descriptionExercice;
       this.imageExercice = imageExercice;
       this.videoExercice = videoExercice;
       this.objectifExercices = objectifExercices;
       this.categorieExercices = categorieExercices;
       this.contenirExercices = contenirExercices;
    }
   
    public Integer getIdExercice() {
        return this.idExercice;
    }
    
    public void setIdExercice(Integer idExercice) {
        this.idExercice = idExercice;
    }
    public String getNomExercice() {
        return this.nomExercice;
    }
    
    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }
    public String getDescriptionExercice() {
        return this.descriptionExercice;
    }
    
    public void setDescriptionExercice(String descriptionExercice) {
        this.descriptionExercice = descriptionExercice;
    }
    public String getImageExercice() {
        return this.imageExercice;
    }
    
    public void setImageExercice(String imageExercice) {
        this.imageExercice = imageExercice;
    }
    public String getVideoExercice() {
        return this.videoExercice;
    }
    
    public void setVideoExercice(String videoExercice) {
        this.videoExercice = videoExercice;
    }
    public Set getObjectifExercices() {
        return this.objectifExercices;
    }
    
    public void setObjectifExercices(Set objectifExercices) {
        this.objectifExercices = objectifExercices;
    }
    public Set getCategorieExercices() {
        return this.categorieExercices;
    }
    
    public void setCategorieExercices(Set categorieExercices) {
        this.categorieExercices = categorieExercices;
    }
    public Set getContenirExercices() {
        return this.contenirExercices;
    }
    
    public void setContenirExercices(Set contenirExercices) {
        this.contenirExercices = contenirExercices;
    }




}


