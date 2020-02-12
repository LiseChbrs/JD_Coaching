/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
function valid(){
    var zone= document.getElementById("zone").value;
    var desc =document.getElementById("desc").value;
    var img = document.getElementById("img").value;
    var vid = document.getElementById("vid").value;
    var text = "Nom exercice : "+zone ;
    if (desc!=null){
        text+= " Description : "+desc+" ";
    }
    text+="</br><IMG style=\"max-width: 100%;\" src=" + img
                                    + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\" > ";
    if (vid!=null){
        text+= "<iframe src=" + vid + " width=\"100%\" frameborder=\"0\" allowfullscreen></iframe>";
    }
    alert(text);
    document.getElementById("popup").innerHTML = text;
    document.getElementById("popupctrl").click();
    
}

function RechercherSeanceNom() {

//var xhr = new XMLHttpRequest();
//
//    //récupérer la valeur dans la zone de texte
//    var recherche = encodeURIComponent(document.getElementById("rechercherSeance").value);
//
//    //si le textbox n'est pas vide, on recherche ce mot dans les séances
//    if (recherche !== "") {
//        xhr.open("GET", "ServletRechercherSeance?nomSeance=" + recherche);
//        xhr.onload = function () {
//            if (xhr.status === 200) {
//
//                //Cacher le zone de texte par défaut
//                document.getElementById("zoneAfficheSeance").style.display = "none";
//
//                //récupérer la réponse de servlet
//                var texte = xhr.responseText;
//
//                //s'il y a pas de réponse, on cache le zone de texte. Sinon, on affiche le nom de la séance                
//                if (texte === "") {
//                    document.getElementById("zoneAfficheSeance").style.display = "none";
//                } else {
//                    document.getElementById("zoneAfficheSeance").style.display = "block";
//                }
//
//                //afficher les séances sur l'interface
//                var elt = document.getElementById("zoneAfficheSeance");
//                elt.innerHTML = texte;
//
//                
//            }
//        };
//        xhr.send();
//    } else {
//        document.getElementById("zoneAfficheSeance").style.display = "none";
//    }    



}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("btnajouter").addEventListener("click", valid);

    document.getElementById("zone").addEventListener("keyup", isExist);
    document.getElementById("catego").addEventListener("mousedown", multichoice);
    document.getElementById("object").addEventListener("mousedown", multichoice);
});

