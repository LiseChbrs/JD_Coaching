/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function isExist() {
    
    
       var xhr = new XMLHttpRequest();
       var param = document.getElementById("zone").value ; 
       var url = "ServletVerifExercice?caractere=" + param ;        
       
        xhr.open("GET",url);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    
         xhr.onload = function()
               {
                   if (xhr.status === 200)
                   {    
                       var dom = document.getElementById("resultat");                     
                       var existe = xhr.responseXML.getElementsByTagName("element")[0].firstChild.nodeValue; 

                       if (existe === "Attention, le nom de l'exercice existe") {
                           dom.innerHTML = existe ; 
                           document.getElementById("btnajouter").disabled = true ; 
                           
                       }else {
                           dom.innerHTML = "";
                           document.getElementById("btnajouter").disabled =false ;
                       }
                   }
               };
               xhr.send(param);    
     
    }
    
function afficherExercice(id){
    var xhr = new XMLHttpRequest();
    var url = "ServletListeExercices?id=" + id ;        

     xhr.open("GET",url);
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


      xhr.onload = function()
            {
                if (xhr.status === 200)
                {    
                    var elt = document.getElementById("zoneExercice");
                    var doc = xhr.responseXML;
                    var element = doc.getElementsByTagName("element");
                    var chaine = "";
                    for (var i = 0; i < name.length; i++){
                       id = element[i].getElementsByTagName("id").firstChild.nodeValue;
                       name = element[i].getElementsByTagName("name").firstChild.nodeValue;
                       image = element[i].getElementsByTagName("image").firstChild.nodeValue;
                       chaine += "<IMG SRC="+image+" ALT="+name+" TITLE="+name+">";
                       chaine += "<input type=\"radio\" name=\"exercice\" value="+id+" />";
                    }
                    elt.innerHTML = chaine;
                    
                }
            };
            xhr.send();  
}


document.addEventListener("DOMContentLoaded", () => {

        document.getElementById("zone").addEventListener("keyup",isExist);

});