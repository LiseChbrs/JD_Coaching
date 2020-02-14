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

/**
 * 
 * fonction popup qui donne les séances impactées par la suppression si besoin
 */
function popSup(id) {


    var xhr = new XMLHttpRequest();

    var url = "ServletSupExo?id=" + id;
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var text;
    var elt = document.getElementById("popup2");
    xhr.onload = function ()
    {
        if (xhr.status === 200) {

            var existe = xhr.responseXML.getElementsByTagName("elements")[0];
            if (existe.children[0].firstChild.nodeValue === "true") {
                var text = "Voulez-vous supprimer l'exercice?";
                text += "</br></br><input type=\"button\" class=\"btn btn-secondary\" id=\"popupsup\" data-dismiss=\"modal\" name=\"btnsupPro\" value=\"Supprimer\" onClick='supPro(" + id + ")' />";
                text+="</br></br><input type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" value=\"Annuler\"/>";
            } else {
                if (existe.children[0].firstChild.nodeValue === "false") {
                    var text = "Cet exercice est un &eacutechauffement ou un &eacutetirement on ne peut pas le supprimer !";
                    text += "</br></br><input type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" value=\"Ok\"/>";
                } else {

                    var text = "<div>Attention la suppression de l'exercice impacte les s&eacuteances suivantes : </div>";
                    for (i = 0; i < existe.children.length; i++) {//je rentre dans elements            
                        text += existe.children[i].children[2].firstChild.nodeValue + "</br>";
                    }
                    text += "</br></br><input type=\"button\"  class=\"btn btn-secondary\" id=\"popupsup\" name=\"btnsupPro\" value=\"Supprimer\" data-dismiss=\"modal\" onClick='supPro(" + id + ")' />";

                    text += "</br></br><input type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" value=\"Annuler\"/>";
                }
            }
            elt.innerHTML = text;
            document.getElementById("popupsup").click();
        }
    };
    xhr.send();
}

/**
 * 
 * fonction de suppression d'un exercice
 */

function accessCollapse() {
    var a = document.getElementById("zone").value;
    var b = document.getElementById("img").value;
    var c = document.getElementById("catego").value;
    var d = document.getElementById("object").value;
    if (a !== "" & b !== "" & c !== "" & d !== "") {
        document.getElementById("").disabled = false;
    }
}

//Cette fonction permet de supprimer un exercice sélectionné
function supPro(id) {

    var xhr = new XMLHttpRequest();
    var url = "ServletSupDefPro?id=" + id;

    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var text;
    var elt = document.getElementById("popup2");
    xhr.onload = function () {
        if (xhr.status === 200) {

        } else {
            alert("ne marche pas");
        }

        window.location.href = "ServletCreationExercice";
    };
    xhr.send();
}
//fonction qui permet de se rendre dans la zone de création d'exercice
function creerexo(url){
    window.location=url;
}

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("btnajouter").addEventListener("click", popup);
    document.getElementById("zone").addEventListener("keyup", isExist);
    document.getElementById("catego").addEventListener("mousedown", multichoice);
    document.getElementById("object").addEventListener("mousedown", multichoice);
    document.getElementById("zone").addEventListener("keyup", accessCollapse);

});


