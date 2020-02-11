/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Cette fonctione nous permet d'afficher une séance par rapport à un programme sélectionné
function afficherSeance() {
    var id = event.target.getAttribute("name");
    var xhr = new XMLHttpRequest();
    var url = "ServletAfficherSeance?id=" + id;

    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.onload = function ()
    {
        if (xhr.status === 200)
        {

            var existe = xhr.responseXML.getElementsByTagName("elements")
            for (i = 0; i < existe.length; i++) {
                for (j = 0; j < existe[i].children.length; j++) {
//                            alert(existe[i].children[j].children[0].firstChild.nodeValue);
//                            alert(existe[i].children[j].children[1].firstChild.nodeValue);
//                            alert(existe[i].children[j].children[2].firstChild.nodeValue);
//                            alert(existe[i].children[j].children[3].firstChild.nodeValue);
                    var elt = document.getElementById(id);
                    alert(elt);
                    var text = "<fieldset> <p>Séance : " + existe[i].children[j].children[0].firstChild.nodeValue + "</p><p>" + existe[i].children[j].children[1].firstChild.nodeValue + "</p><p>" + existe[i].children[j].children[2].firstChild.nodeValue + "</p><p>" + existe[i].children[j].children[3].firstChild.nodeValue + "</p></fieldset> ";
                    elt.innerHTML = text;
                }

            }

        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("huitre").addEventListener("click", afficherSeance);

});