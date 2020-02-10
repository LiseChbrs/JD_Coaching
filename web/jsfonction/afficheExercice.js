/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function afficherExercice(id) {
    var xhr = new XMLHttpRequest();
    var url = "ServletListeExercices?id=" + id;

    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {
            var elt = document.getElementById("zoneExercice");
            elt.innerHTML = "</br>";
            var doc = xhr.responseXML;
            if (doc === null) {
                elt.insertAdjacentHTML("beforeend", "<h3> Veuillez re-cliquer une nouvelle fois sur la catégorie voulue svp. </h3>");
            } else {
                var element = doc.getElementsByTagName("element");
                if (element.length === 0) {
                    elt.insertAdjacentHTML("beforeend", "<h1> Pas d'exercice correspondant. </h1>");
                } else {
                    var id = doc.getElementsByTagName("id");
                    var name = doc.getElementsByTagName("name");
                    var image = doc.getElementsByTagName("image");
                    var chaine = "";

                    for (var i = 0; i < element.length; i++) {
                        chaine = "";
                        chaine += "<IMG SRC=\"" + image[i].firstChild.nodeValue + "\" ALT=\"" + name[i].firstChild.nodeValue + "\" width=\"100\"height=\"100\" TITLE=\"" + name[i].firstChild.nodeValue + "\">";
                        chaine += "<input type=\"radio\" id=\"exo\" name=\"exercice\" value=\"" + id[i].firstChild.nodeValue + "\"/>";
                        elt.insertAdjacentHTML("beforeend", chaine);
                    }
                    elt.insertAdjacentHTML("afterend", "<span id=\"affiche")
                    document.getElementById("exo").addEventListener("click", choixExo);
                }
            }
        }
    };
    xhr.send();
}

function choixExo(){
    
}

