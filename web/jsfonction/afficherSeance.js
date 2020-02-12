/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


   /**
    * Fonction de recherce d'une séance avec filtrage
    */
function RechercherSeance() {
    
    /**
     * 
     * @type Element
     */
    
    var input, filter, ul, elt, a, i, txtValue;
    
    
    input = document.getElementById("rechercherSeance");
    filter = input.value.toUpperCase();
    ul = document.getElementById("listeSeance");
    elt = ul.getElementsByTagName("li");
    
    for (i = 0; i < elt.length; i++) {
        a = elt[i].getElementsByTagName("button")[0];
        txtValue = a.textContent || a.innerText;
        
        
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            elt[i].style.display = "";
        } else {
            elt[i].style.display = "none";
        }
    }
}


/**
 * 
 * Fonction qui affiches les exercices d'une séances avec ses propriétés, pour un clic sur la séance 
 */
function afficherExerciceSeance() {
    
    /**
     * 
     * @type type
     */
    var id = event.target.getAttribute("id");
    var xhr = new XMLHttpRequest();
    var url = " ServletAfficherExercice?id=" + id;
    
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var elt = document.getElementById("0" +id);
            if (elt.style.display === "none") {
                elt.style.display = "block";
                if (elt.firstChild.nodeName !== "DIV") {
                    var existe = xhr.responseXML.getElementsByTagName("elements")
                    var text1 = "<ul class=\"list-group list-group-flush\">"
                    for (i = 0; i < existe.length; i++) {//je rentre dans la séance
                        for (j = 0; j < existe[i].children.length; j++) {//je récupère les elements de chaque exercice
                            text1 += "<li class=\"list-group-item\">   " +
                                    "Exercice : " + existe[i].children[j].children[1].firstChild.nodeValue + " - " + existe[i].children[j].children[2].firstChild.nodeValue
                                    + " </br><IMG style=\"max-width: 100%;\" src=" + existe[i].children[j].children[3].firstChild.nodeValue
                                    + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > " +
                                    "<iframe src=" + existe[i].children[j].children[4].firstChild.nodeValue + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe> " +
                                    "</li>";
                        }
                    }
                    text1 += "</ul>";
                    elt.innerHTML = text1;
                }
            } else {
                elt.style.display = "none";
            }
        }
    };
    xhr.send();
}
document.addEventListener("DOMContentLoaded", () => {
    for (i = 0; i < document.getElementsByClassName("btn btn-outline-warning").length; i++) {
        document.getElementsByClassName("btn btn-outline-warning")[i].addEventListener("click", afficherExerciceSeance);
    }
});