/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


   /**
    * Fonction de recherce d'une séance 
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
        a = elt[i].getElementsByTagName("a")[0];
        txtValue = a.textContent || a.innerText;
        
        
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            elt[i].style.display = "";
        } else {
            elt[i].style.display = "none";
        }
    }
}

function myFunction() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}