package metier;
// Generated 8 fevr. 2020 16:33:01 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Difficulte generated by hbm2java
 */
public class Difficulte  implements java.io.Serializable {


     private Integer idDifficulte;
     private String nomDifficulte;
     private Set seances = new HashSet(0);

    public Difficulte() {
    }

    public Difficulte(Integer idDifficulte, String nomDifficulte) {
        this.idDifficulte = idDifficulte;
        this.nomDifficulte = nomDifficulte;
    }

	
    public Difficulte(String nomDifficulte) {
        this.nomDifficulte = nomDifficulte;
    }
    public Difficulte(String nomDifficulte, Set seances) {
       this.nomDifficulte = nomDifficulte;
       this.seances = seances;
    }
   
    public Integer getIdDifficulte() {
        return this.idDifficulte;
    }
    
    public void setIdDifficulte(Integer idDifficulte) {
        this.idDifficulte = idDifficulte;
    }
    public String getNomDifficulte() {
        return this.nomDifficulte;
    }
    
    public void setNomDifficulte(String nomDifficulte) {
        this.nomDifficulte = nomDifficulte;
    }
    public Set getSeances() {
        return this.seances;
    }
    
    public void setSeances(Set seances) {
        this.seances = seances;
    }




}


