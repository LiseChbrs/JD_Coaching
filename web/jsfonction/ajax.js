/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * ==================Page javascript de Exercice.jsp=======================
 */


/**
 * 
 * fonction qui gère les listes déroulantes multichoix 
 */
function multichoice() {

    var el = event.target;


    if (el.tagName.toLowerCase() === 'option' && el.parentNode.hasAttribute('multiple')) {
        event.preventDefault();

        // toggle selection
        if (el.hasAttribute('selected')) {

            el.removeAttribute('selected');
        } else {
            el.setAttribute('selected', '');
        }

    }
}

/**
 * 
 * fonction qui vérifie qu'un exercice est unique 
 */
function isExist() {


    var xhr = new XMLHttpRequest();
    var param = document.getElementById("zone").value.toUpperCase();
    var url = "ServletVerifExercice?caractere=" + param;

    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var dom = document.getElementById("resultat");
            var existe = xhr.responseXML.getElementsByTagName("element")[0].firstChild.nodeValue;

            if (existe === "Attention, le nom de l'exercice existe") {
                dom.innerHTML = existe;
                document.getElementById("btnajouter").disabled = "disabled";

            } else {
                dom.innerHTML = "";
                document.getElementById("btnajouter").disabled = false;
            }
        }
    };
    xhr.send(param);

}

/**
 * 
 * fonction popup qui récapitule les informations saisies 
 */
function popup() {
    var zone = document.getElementById("zone").value;
    var desc = document.getElementById("desc").value;
    var img = document.getElementById("img").value;
    var vid = document.getElementById("vid").value;
    var text = "Nom exercice : " + zone;
    if (desc != "") {
        text += "</br> Description : " + desc + " ";
    }
    text += "</br><IMG style=\"max-width: 100%;\" src=" + img
            + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > ";
    if (vid != "") {
        text += "<iframe src=" + vid + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe>";
    }
    document.getElementById("popup").innerHTML = text;
    document.getElementById("popupctrl").click();

}

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("btnajouter").addEventListener("click", popup);
    document.getElementById("zone").addEventListener("keyup", isExist);
    document.getElementById("catego").addEventListener("mousedown", multichoice);
    document.getElementById("object").addEventListener("mousedown", multichoice);

});

