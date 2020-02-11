/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cette fonction prend id d'une categorie d'exercice afin d'afficher les exercices qui en font partie
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
                        chaine += "<input type=\"radio\" id=\"exo\" onClick=\"choixChrono()\" name=\"exercice\" value=\"" + id[i].firstChild.nodeValue + "\"/>";
                        elt.insertAdjacentHTML("beforeend", chaine);
                    }

                    elt.insertAdjacentHTML("beforeend", "<div id=\"choix_chrono\" ></div>");
                }
            }
        }
    };
    xhr.send();
}

/*
 * Cette fonction permet d'afficher les deux radio button qui permettent de choisir si l'exercice choisi est chronométré ou non
 */
function choixChrono() {
    var doc = document.getElementById("choix_chrono");
    doc.innerHTML = "<label for=\"chrono_1\"  >Chrono</label><input type=\"radio\" name=\"choix\" value=\"chrono_1\" id =\"chrono_1\">"
            + "<label for=\"chrono_2\" >Standard</label><input type=\"radio\" name=\"choix\" value=\"chrono_2\" id =\"chrono_2\">";
    doc.insertAdjacentHTML("beforeend", "<div id=\"affiche_champs\"> </div> ");
    document.getElementById("chrono_1").addEventListener("click", choixExo1);
    document.getElementById("chrono_2").addEventListener("click", choixExo2);

}
/*
 * Cette fonction permet s'active au click sur le boutton correspondant à chrono et affiches les textbox correspondant
 */
function choixExo1() {

    var doc = document.getElementById("affiche_champs");
    doc.innerHTML = "<input type=\"text\" name=\"duree\" id=\"duree\" value=\"24\"  placeholder=\"Durée de l'occurrence\" required=\"required\"/><br> "
            + "<input type=\"text\" name=\"nboccurence\" id=\"nboccurence\" value=\"24\" placeholder=\"Nombre d'occurences\" required=\"required\" /><br> "
            + "<input type=\"text\" name=\"tempspause\" id=\"tempspause\" value=\"24\" placeholder=\"Temps de pause\" required=\"required\"/><br> "
            + "<input type=\"hidden\" name=\"nbrepetition\" id=\"nbrepetition\" value=\"\" />"
            + "<input type=\"button\" id=\"btnEnregistrerExo\" onClick=\"enregistrementSession()\" value=\"Enregistrer l'exercice\" > ";
}
/*
 * Cette fonction permet s'active au click sur le boutton correspondant à  standard et affiches les textbox correspondant
 */

function choixExo2() {
    var doc = document.getElementById("affiche_champs");
    doc.innerHTML = "<input type=\"text\" name=\"nbrepetition\" id=\"nbrepetition\" value=\"24\" placeholder=\"Nombre de répétitions\" required=\"required\" /><br> "
            + "<input type=\"text\" name=\"nboccurence\" id=\"nboccurence\" value=\"24\" placeholder=\"Nombre d'occurences\" required=\"required\" /><br> "
            + "<input type=\"text\" name=\"tempspause\" id=\"tempspause\" value=\"24\" placeholder=\"Temps de pause\" required=\"required\" /><br> "
            + "<input type=\"hidden\" name=\"duree\" id=\"duree\" value=\"\" />"
            + "<input type=\"button\" id=\"btnEnregistrerExo\" onClick=\"enregistrementSession()\" value=\"Enregistrer l'exercice\" > ";


}
/*
 * En cliquant sue enregistrer exercice, on va mettre l'exercice correspondant en session via une servlet afin de pouvoir récupérer le numéro d'ordre de l'exercice dans une séance
 */

function enregistrementSession() {

    var xhr = new XMLHttpRequest();

    if (document.getElementById("nbrepetition").value === "") {
        var duree = document.getElementById("duree").value;
        var nbrepetition = "";
    } else {
        var nbrepetition = document.getElementById("nbrepetition").value;
        var duree = "";
    }

    var tempspause = document.getElementById("tempspause").value;
    var nboccurence = document.getElementById("nboccurence").value;

    var idEx = document.querySelector('input[name="exercice"]:checked').value;
    alert(idEx);
    var url = "ServletEnregistrementSession?duree=" + duree + "&nbrepetition=" + nbrepetition + "&tempspause=" + tempspause + "&nboccurence=" + nboccurence + "&idEx=" + idEx;
    alert(url);

    xhr.open("GET", url);
    xhr.onload = function () {

        if (xhr.status === 200) {
            alert("bien executer");

        }
    };
    xhr.send();
}






