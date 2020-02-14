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
            var elt = document.getElementById("0" + id);
            if (elt.style.display === "none") {
                elt.style.display = "block";
                if (elt.firstChild.nodeName !== "DIV") {
                    var existe = xhr.responseXML.getElementsByTagName("elements")
                    var text1 = "<ul class=\"list-group list-group-flush\">"
                    for (i = 0; i < existe.length; i++) {//je rentre dans la séance
                        for (j = 0; j < existe[i].children.length; j++) {//je récupère les elements de chaque exercice

                            text1 += "<li class=\"list-group-item\">   " +
                                    "<strong>Exercice :</strong> " + existe[i].children[j].children[1].firstChild.nodeValue
                            if (existe[i].children[j].children[2].firstChild.nodeValue !== "null") {
                                text1 += " - " + existe[i].children[j].children[2].firstChild.nodeValue
                            }

                            text1 += " </br><IMG style=\"max-width: 100%;\" src=" + existe[i].children[j].children[3].firstChild.nodeValue
                                    + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > "
                            if (existe[i].children[j].children[4].firstChild.nodeValue !== "null") {
                                text1 += "<iframe src=" + existe[i].children[j].children[4].firstChild.nodeValue + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe> "
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
/* fonction permettant de selectionner plusieurs choix dans une liste
 * 
 */
function multichoice1() {
    var el = event.target;
    if (el.tagName.toLowerCase() === 'option' && el.parentNode.hasAttribute('multiple')) {
        event.preventDefault();
        if (el.hasAttribute('selected')) {

            el.removeAttribute('selected');
        } else {
            el.setAttribute('selected', '');
        }

    }
}

/* fonction permettant de disable une liste si on en a pas besoin et la remettre à zéro
 * 
 */
        
function disablelist(){
    if(document.getElementById("typeSeanceVisible").a==="1"){
        document.getElementById("typeSeanceVisible").checked = true;
        document.getElementById("typeSeanceVisible").a="0";
        document.getElementById("cacher").name="difficulte";
        document.getElementById("selectDifficulte").name="";
document.getElementById("selectDifficulte").selectedIndex=0;        
    }
    else {
        document.getElementById("typeSeanceCache").checked=true;

        document.getElementById("typeSeanceVisible").a="1";
        document.getElementById("cacher").name="";
        document.getElementById("selectDifficulte").name="difficulte";


    }
    
    if (document.getElementById("typeSeanceVisible").checked){
        document.getElementById("selectDifficulte").disabled = true
        
    }else{
        document.getElementById("selectDifficulte").disabled = false
    }
}
/*
 * fonction permettant de selectionner une image et changer son css
 */

function selection(){
    for (i=0;i<document.getElementsByName("imgalpha").length;i++){
        document.getElementsByName("imgalpha")[i].style="";

    }
        event.target.style="width:100px;height:100px;border:3px solid #1b7484;"
}

/* fonction permettant de selectionner un bouton
 * 
 */
        
function selectionbut(){
    for (i=0;i<document.getElementsByName("buttalpha").length;i++){
       slt=document.getElementsByName("buttalpha")[i].getAttribute("style")
        if(slt.includes("block")){
            document.getElementsByName("buttalpha")[i].setAttribute("style","");
        }
        

    }
        stl=event.target.getAttribute('style');
       event.target.setAttribute("style",stl+"display:in-block;width:150px;height: 30px;border:2px solid #0fc61f;border-radius:10px;")
}
//fonction qui permet d'aller directement à la création d'une séance
function creerseance(url){
    window.location=url;
}





document.addEventListener("DOMContentLoaded", () => {
    for (i = 0; i < document.getElementsByClassName("btn btn-outline-warning").length; i++) {
        document.getElementsByClassName("btn btn-outline-warning")[i].addEventListener("click", afficherExerciceSeance);
    }
        document.getElementById("categorie1").addEventListener("mousedown", multichoice1);
        
        
        for (i=0;i<document.getElementsByClassName("1").length;i++){
            document.getElementsByClassName("1")[i].addEventListener("click",disablelist)
        }
        
        for(i=0;i<document.getElementsByName("buttalpha").length;i++){
        document.getElementsByName("buttalpha")[i].addEventListener("click",selectionbut)
    }
});