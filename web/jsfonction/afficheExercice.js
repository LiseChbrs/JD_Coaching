/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function switchRadio() {
    var radioBtnCache = document.getElementById("typeSeanceCache");
    var radioBtnVisible = document.getElementById("typeSeanceVisible");
    var selection = document.getElementById("selectDifficulte");

    if (radioBtnCache.checked) {
//        radioBtnCache.checked = "";
//        radioBtnVisible.checked = "checked";
        selection.disabled = "disabled";
    } else {
//        radioBtnCache.checked = "checked";
//        radioBtnVisible.checked = "";
        selection.disabled = "";
    }
}

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
                    elt.insertAdjacentHTML("beforeend", "<h3> Pas d'exercice correspondant. </h3>");
                } else {
                    var id = doc.getElementsByTagName("id");
                    var name = doc.getElementsByTagName("name");
                    var image = doc.getElementsByTagName("image");
                    var chaine = "";

                    for (var i = 0; i < element.length; i++) {
                        chaine = "";
                        chaine += "<label for=\"exo" + id[i].firstChild.nodeValue + "\"><IMG id=\"image_" + id[i].firstChild.nodeValue + "\"SRC=\"" + image[i].firstChild.nodeValue + "\" ALT=\"" + name[i].firstChild.nodeValue + "\" width=\"100\"height=\"100\" TITLE=\"" + name[i].firstChild.nodeValue + "\" name=\"imgalpha\"></label>";
                        chaine += "<input style=\"display:none;\" type=\"radio\" id=\"exo" + id[i].firstChild.nodeValue + "\" onClick=\"choixChrono()\" name=\"exercice\" value=\"" + id[i].firstChild.nodeValue + "\"/> &nbsp&nbsp&nbsp";
                        elt.insertAdjacentHTML("beforeend", chaine);
                    }

                    elt.insertAdjacentHTML("beforeend", "<div id=\"choix_chrono\" ></div>");
                }
            } for(i=0;i<document.getElementsByName("imgalpha").length;i++){
        document.getElementsByName("imgalpha")[i].addEventListener("click",selection)
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
    doc.innerHTML = "<label for=\"chrono_1\"  >Chrono</label>&nbsp<input type=\"radio\" name=\"choix\" value=\"chrono_1\" id =\"chrono_1\"> &nbsp&nbsp&nbsp "
            + "<label for=\"chrono_2\" >Standard</label>&nbsp<input type=\"radio\" name=\"choix\" value=\"chrono_2\" id =\"chrono_2\">";
    doc.insertAdjacentHTML("beforeend", "<div id=\"affiche_champs\"> </div> ");
    document.getElementById("chrono_1").addEventListener("click", choixExo1);
    document.getElementById("chrono_2").addEventListener("click", choixExo2);

}
/*
 * Cette fonction permet s'active au click sur le boutton correspondant à chrono et affiches les textbox correspondant
 */
function choixExo1() {

    var doc = document.getElementById("affiche_champs");
    doc.innerHTML = "<input type=\"number\" name=\"duree\"step=\"0.01\" id=\"duree\" value=\"24\"  placeholder=\"Durée de l'occurrence\" required=\"required\"/><br> "
            + "<input type=\"number\" name=\"nboccurence\" id=\"nboccurence\" value=\"24\" placeholder=\"Nombre d'occurences\" required=\"required\" /><br> "
            + "<input type=\"number\" name=\"tempspause\" step=\"0.01\" id=\"tempspause\" value=\"24\" placeholder=\"Temps de pause\" required=\"required\"/><br> "
            + "<input type=\"hidden\" name=\"nbrepetition\" id=\"nbrepetition\" value=\"\" />"
            + "<input type=\"button\" id=\"btnEnregistrerExo\" onClick=\"enregistrementSession()\" value=\"Enregistrer l'exercice\" > ";
}
/*
 * Cette fonction permet s'active au click sur le boutton correspondant à  standard et affiches les textbox correspondant
 */

function choixExo2() {
    var doc = document.getElementById("affiche_champs");
    doc.innerHTML = "<input type=\"number\" name=\"nbrepetition\" id=\"nbrepetition\" value=\"24\" placeholder=\"Nombre de répétitions\" required=\"required\" /><br> "
            + "<input type=\"number\" name=\"nboccurence\" id=\"nboccurence\" value=\"24\" placeholder=\"Nombre d'occurences\" required=\"required\" /><br> "
            + "<input type=\"number\" step=\"0.01\" name=\"tempspause\" id=\"tempspause\" value=\"24\" placeholder=\"Temps de pause\" required=\"required\" /><br> "
            + "<input type=\"hidden\" name=\"duree\" id=\"duree\" value=\"\" />"
            + "<input type=\"button\" id=\"btnEnregistrerExo\" onClick=\"enregistrementSession()\" value=\"Enregistrer l'exercice\" > ";


}
/*
 * En cliquant sue enregistrer exercice, on va mettre l'exercice correspondant en session via une servlet 
 * afin de pouvoir récupérer le numéro d'ordre de l'exercice dans une séance
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

    var url = "ServletEnregistrementSession?duree=" + duree + "&nbrepetition=" + nbrepetition + "&tempspause=" + tempspause + "&nboccurence=" + nboccurence + "&idEx=" + idEx;

    xhr.open("GET", url);
    xhr.onload = function () {

        if (xhr.status === 200) {

            var zone;

            var nombre = document.getElementsByName("imgSession").length;
            var listCatEx = document.getElementById("listCatEx").childNodes;
            var image = document.getElementById("image_" + idEx).cloneNode(true);
            image.id = nombre;
            image.name = "imgSession";
            /*
             * Si nombre === 1, alors on a un exercice de catégorie échauffement
             * Donc a enregistrer en premier.
             */
            if (nombre === 1) {
                zone = document.getElementById("gauche");
                for (var i = 1; i < listCatEx.length - 1; i++) {
                    if (listCatEx[i].value === "Etirement") {
                        listCatEx[i].style = "";
                    } else {
                        listCatEx[i].style = "display:none";
                    }
                }
                /*
                 * Si nombre === 2, alors on a un exercice de catégorie étirement
                 * Donc a enregistrer en dernier.
                 */
            } else if (nombre === 2) {
                zone = document.getElementById("droite");
                for (var i = 1; i < listCatEx.length - 1; i++) {
                    if (listCatEx[i].value === "Etirement" || listCatEx[i].value === "Echauffement") {
                        listCatEx[i].style = "display:none";
                    } else {
                        listCatEx[i].style = "";
                    }
                }
                document.getElementById("btnEnregistrerSeance").disabled = "";
                /*
                 * Sinon on ajoute les images dans la div centrale.
                 */
            } else {
                zone = document.getElementById("centrale");
                image.addEventListener("click", suppressionSession);
            }

            zone.appendChild(image);
            //Puis on vide le contenu des div contenant les exercices et les textbox pour
            //les exercices.
            document.getElementById("zoneExercice").innerHTML = "";


        }
    };
    xhr.send();
}

/*
 * On supprime l'exercice de la session au click puis on
 * enlève l'image de la div centrale.
 */
function suppressionSession() {
    var imgSupp = this.id;

    var xhr = new XMLHttpRequest();
    var url = "ServletSuppressionSession?position=" + imgSupp;
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function ()
    {
        if (xhr.status === 200) {
            var zone = document.getElementById("centrale").childNodes;
            var aleat = document.getElementById("centrale");
            aleat.removeChild(zone[imgSupp - 3]);

            for (var i = 0; i < zone.length; i++) {
                zone[i].id = parseInt(i + 3, 10);
            }
        }
    };

    xhr.send();
}


