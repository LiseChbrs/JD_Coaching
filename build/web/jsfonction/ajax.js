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


document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("zone").addEventListener("keyup", isExist);
    document.getElementById("catego").addEventListener("mousedown", multichoice);
    document.getElementById("object").addEventListener("mousedown", multichoice);
});

