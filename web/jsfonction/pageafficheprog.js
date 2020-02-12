/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

   /**
    * Fonction de recherce d'un programme avec filtrage
    */
function RechercherProgramme() {
    
    /**
     * 
     * @type Element
     */
    
    var input, filter, ul, elt, a, i, txtValue;
    
    
    input = document.getElementById("rechercherProg");
    filter = input.value.toUpperCase();
    ul = document.getElementById("listeProg");
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




//Cette fonction nous permet d'afficher une séance par rapport à un programme sélectionné
function afficherSeance() { //affichage de la seance en fonction du programme
    var id = event.target.getAttribute("name");
    var xhr = new XMLHttpRequest();
    var url = "ServletAfficherSeance?id=" + id;
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var elt = document.getElementById(id);
            if (elt.style.display === "none") {
                elt.style.display = "block";
                if (elt.firstChild.nodeName !== "DIV") {
                    var existe = xhr.responseXML.getElementsByTagName("elements")
                    var text1 = "<div class=\"accordion\" id=\"accordionExample\"><ul class=\"list-group list-group-flush\">"
                    for (i = 0; i < existe.length; i++) {//je rentre dans elements
                        for (j = 0; j < existe[i].children.length; j++) {//je rentre dans chaque seance et j'affiche
                            text1 += "<li class=\"list-group-item\"><div class=\"card\"><div class=\"card-header\" >  " +
                                    "<button id = 0" + existe[i].children[j].children[0].firstChild.nodeValue +
                                    " class=\"btn btn-link collapsed\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\"> " +
                                    "Seance : " + existe[i].children[j].children[1].firstChild.nodeValue + " - "
                                    + existe[i].children[j].children[2].firstChild.nodeValue + " </br> "
                                    + existe[i].children[j].children[3].firstChild.nodeValue + "</button " +
                                    "<div id=\"collapseOne\" class=\"collapse\" aria-labelledby=\"headingOne\" data-parent=\"#accordionExample\"> " +
                                    "<div class=\"card-body\"  style =\"display: none\" id=00" + existe[i].children[j].children[0].firstChild.nodeValue
                                    + ">  </div> </div></li>";
                        }
                    }
                    text1 += "</ul></div>";
                    elt.innerHTML = text1;
                    for (i = 0; i < document.getElementsByClassName("btn btn-link collapsed").length; i++) {//creation des event pour les sessions
                        document.getElementsByClassName("btn btn-link collapsed")[i].addEventListener("click", afficherExercice);
                    }
                }
            } else {
                elt.style.display = "none";
            }
        }
    };
    xhr.send();
}
function afficherExercice() {//affichage des exercices d'une séance
    var id = event.target.getAttribute("id");
    var xhr = new XMLHttpRequest();
    var url = "ServletAfficherExercice?id=" + id;
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var elt = document.getElementById("0" + id);
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
        document.getElementsByClassName("btn btn-outline-warning")[i].addEventListener("click", afficherSeance);
    }
});