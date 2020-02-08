package metier;
// Generated 8 fevr. 2020 16:33:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * CategorieExercice generated by hbm2java
 */
public class CategorieExercice  implements java.io.Serializable {


     private Integer idCategorieExercice;
     private String nomCategorieExercice;
     private String descriptionCategorieExercice;
     private Set exercices = new HashSet(0);

    public CategorieExercice() {
    }

	
    public CategorieExercice(String nomCategorieExercice) {
        this.nomCategorieExercice = nomCategorieExercice;
    }
    public CategorieExercice(String nomCategorieExercice, String descriptionCategorieExercice, Set exercices) {
       this.nomCategorieExercice = nomCategorieExercice;
       this.descriptionCategorieExercice = descriptionCategorieExercice;
       this.exercices = exercices;
    }
   
    public Integer getIdCategorieExercice() {
        return this.idCategorieExercice;
    }
    
    public void setIdCategorieExercice(Integer idCategorieExercice) {
        this.idCategorieExercice = idCategorieExercice;
    }
    public String getNomCategorieExercice() {
        return this.nomCategorieExercice;
    }
    
    public void setNomCategorieExercice(String nomCategorieExercice) {
        this.nomCategorieExercice = nomCategorieExercice;
    }
    public String getDescriptionCategorieExercice() {
        return this.descriptionCategorieExercice;
    }
    
    public void setDescriptionCategorieExercice(String descriptionCategorieExercice) {
        this.descriptionCategorieExercice = descriptionCategorieExercice;
    }
    public Set getExercices() {
        return this.exercices;
    }
    
    public void setExercices(Set exercices) {
        this.exercices = exercices;
    }




}

