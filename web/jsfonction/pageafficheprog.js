/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function afficherSeanceBilan(){
    var xhr = new XMLHttpRequest();
    var url = "ServletSeanceBilan";
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var doc = document.getElementById("champsProgrammes");
            var text = "";
            var zone = xhr.responseXML;
            var list = zone.getElementsByTagName("element");
            for(var i = 0; i < list.length; i++) { 
                text += "<label name=\"labelle\"  for=\"000000"+ zone.getElementsByTagName("id")[i].firstChild.nodeValue +"\" >" +
                        "<div class=\"card\" name=\"carte\"style=\"width: 20%;float=\"left\";\">" +
                        "<div class=\"card-header\" id=\"headingOne\">" +
                        " <li class=\"list-group-item\"><button id= \"" + zone.getElementsByTagName("id")[i].firstChild.nodeValue + "\"" + 
                        " class=\"btn btn-outline-warning\" i=\"000000"+ zone.getElementsByTagName("id")[i].firstChild.nodeValue +"\" onclick=\"afficherExerciceSeance()\" type=\"button\" " + 
                        "data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" " +
                        "aria-controls=\"collapseOne\" > Seance " + zone.getElementsByTagName("id")[i].firstChild.nodeValue + " : " + zone.getElementsByTagName("nom")[i].firstChild.nodeValue + " </button></li>" +
                        "</div>" +
                        "<div id=\"zone_" + zone.getElementsByTagName("id")[i].firstChild.nodeValue + "\" style =\"display: none\">" +
                        "</div></div><input name=\"rbt\" type=\"radio\" style=\"display : inline\" id=\"000000"+ zone.getElementsByTagName("id")[i].firstChild.nodeValue +"\"/>";
            }for(i=0;i<document.getElementsByName("labelle").length;i++){
                document.getElementsByName("labelle")[i].addEventListener("click",selectionradio)
            }
            doc.innerHTML = text ;
        }
    };
    xhr.send();
}         


function selectionradio(){
    var e =event.target.getAttribute("i");
    document.getElementById(e).checked;
}
/**
 * 
 * fonction qui affiche le détails des exercices lors du choix de la seance a integrer dans le programme 
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
            var elt = document.getElementById("zone_" + id);
                    
            if (elt.style.display === "none") {
                elt.style = "display: block";
                if (elt.nodeValue === null) {
                
                    var existe = xhr.responseXML.getElementsByTagName("element");
                    
                    var text1 = "<ul class=\"list-group list-group-flush\">";
                    
                    for (i = 0; i < existe.length; i++) {//je rentre dans la séance

                           //numO
                            text1 += "<li class=\"list-group-item\">   " +
                                    "Exercice : " + existe[i].children[5].firstChild.nodeValue;
                            //nom
                            if (existe[i].children[1].firstChild.nodeValue !== "null") {
                                text1 += " - " + existe[i].children[1].firstChild.nodeValue;
                            }
                            //description
                            if (existe[i].children[2].firstChild.nodeValue !== "null") {
                                text1 += " - " + existe[i].children[2].firstChild.nodeValue;
                            }
                            //image
                            text1 += " </br><IMG style=\"max-width: 100%;\" src=" + existe[i].children[3].firstChild.nodeValue
                                    + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > ";
                            
                            //video
                            if (existe[i].children[4].firstChild.nodeValue !== "null") {
                                text1 += "<iframe src=" + existe[i].children[4].firstChild.nodeValue + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe> "
                            }
                    
                            //nombre d'occurence
                            if (existe[i].children[6].firstChild.nodeValue !== "null") {
                                text1 += " </br> Nombre d'occurence : " + existe[i].children[6].firstChild.nodeValue;    
                            }
                            //nombre de repetition
                            if (existe[i].children[7].firstChild.nodeValue !== "null") {
                                text1 += " </br> Nombre de repetition : " + existe[i].children[7].firstChild.nodeValue;
                            }                            
                            //duree
                            if (existe[i].children[8].firstChild.nodeValue !== "null") {
                                text1 += " </br> Durée : " + existe[i].children[8].firstChild.nodeValue;
                            }
                            //tps pause
                            if (existe[i].children[9].firstChild.nodeValue !== "null") {
                                text1 += " </br> Temps de pause : " + existe[i].children[9].firstChild.nodeValue;
                            }                        
                        
                            text1 += "</li>";
                        
                    }
                    text1 += "<button id=\"choixSeance\" value=\"" + existe[i].children[0].firstChild.nodeValue + "\" onclick=\"ChoixSeance()\" type=\"button\">Choisir cette seance</button>";
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


/**
 * 
 * Fonction qui renvoie à la servlet d'enregistrement d'une seance 
 */
function ChoixSeance() {
    var param = document.getElementById("choixSeance").value;
    
    var xhr = new XMLHttpRequest();
    var url = " ServletChoixSeance?choix=" + param;
    
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            document.getElementById("champsProgrammes").innerHTML = "";
            if(document.getElementById("valeurCachee").value.length === "1"){
                document.getElementById("btnTypeStandard").style.display = "inline";
            } else if(document.getElementById("valeurCachee").value.length === "5"){
                document.getElementById("btnTypeStandard").style.display = "none";   
            } else {
                document.getElementById("valeurCachee").value += "0";
            }
            
            var idEx = document.querySelector('input[name="carte"]:checked').value;
            
            var nombre = document.getElementsByName("imgSession").length;
            var listCatEx = document.getElementById("listCatEx").childNodes;
            var image = document.getElementById("image_" + idEx).cloneNode(true);
            image.id = nombre;
            image.name = "imgSession";
            
        }
    } 
} 

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
                    var existe = xhr.responseXML.getElementsByTagName("elements");
                    var text1 = "<div class=\"accordion\" id=\"accordionExample\"><ul class=\"list-group list-group-flush\">";
                    for (i = 0; i < existe.length; i++) {//je rentre dans elements
                        for (j = 0; j < existe[i].children.length; j++) {//je rentre dans chaque seance et j'affiche
                            text1 += "<li  class=\"list-group-item\"><div class=\"card\"><div class=\"card-header\" >  " +
                                    "<button name=" + id + " id =0" + existe[i].children[j].children[0].firstChild.nodeValue +
                                    " class=\"btn btn-link collapsed\" style=\"width: 100%;background-color:#CAECF4;\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" aria-controls=\"collapseOne\"> " +
                                    "Seance " + existe[i].children[j].children[4].firstChild.nodeValue + " : " + existe[i].children[j].children[1].firstChild.nodeValue + " - "
                                    + existe[i].children[j].children[2].firstChild.nodeValue + " </br> "
                                    + existe[i].children[j].children[3].firstChild.nodeValue + "</button " +
                                    "<div id=\"collapseOne\" class=\"collapse\" aria-labelledby=\"headingOne\" data-parent=\"#accordionExample\"> " +
                                    "<div class=\"card-body\" name=\"coucou\" style =\"display: none\" id=0" + id + "0" + existe[i].children[j].children[0].firstChild.nodeValue
                                    + ">  </div> </div></li>";
                        }
                    }
                    text1 += "</ul></div></div>";
                    elt.innerHTML = text1;
                    idP = id;
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
    var idP = event.target.getAttribute("name");
    var xhr = new XMLHttpRequest();
    var url = "ServletAfficherExercice?id=" + id;
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var elt = document.getElementById("0" + idP + id);

            if (elt.style.display === "none") {
                elt.style.display = "block";
                if (elt.firstChild.nodeName !== "DIV") {
                    var existe = xhr.responseXML.getElementsByTagName("elements");
                    var text1 = "<ul class=\"list-group list-group-flush\">";
                    for (i = 0; i < existe.length; i++) {//je rentre dans la séance
                        for (j = 0; j < existe[i].children.length; j++) {//je récupère les elements de chaque exercice
                            text1 += "<li class=\"list-group-item\">   " +
                                    "<strong> Exercice " + existe[i].children[j].children[5].firstChild.nodeValue + " : " + existe[i].children[j].children[1].firstChild.nodeValue + "</strong> - ";
                            if (existe[i].children[j].children[2].firstChild.nodeValue !== "null") {
                                text1 += "</br>" + existe[i].children[j].children[2].firstChild.nodeValue;
                            }
                            text1 += "</br>" + existe[i].children[j].children[6].firstChild.nodeValue + " x ";
                            if (existe[i].children[j].children[7].firstChild.nodeValue !== "null") {
                                text1 += existe[i].children[j].children[7].firstChild.nodeValue + " r&eacutep&eacutetions ";
                            }
                            if (existe[i].children[j].children[8].firstChild.nodeValue !== "null") {
                                text1 += existe[i].children[j].children[8].firstChild.nodeValue + " min";
                            }
                            text1 += "avec " + existe[i].children[j].children[9].firstChild.nodeValue + " min de pause entre chaque s&eacuterie.";
                            text1 += " </br><IMG style=\"max-width: 100%;\" src=" + existe[i].children[j].children[3].firstChild.nodeValue
                                    + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > ";
                            if (existe[i].children[j].children[4].firstChild.nodeValue !== "null") {
                                text1 += "<iframe src=" + existe[i].children[j].children[4].firstChild.nodeValue + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe> ";
                            }

                            text1 += "</li>";

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