<meta charset="utf-8">
<?php
    session_start();
    //charger le fichier de fonctions
    require('fonctionsUtiles.php');
    //exécuter la fonction
    $co= connexion_choix();  
    
//Récupération du commentaire concerné par le j'aime    
    $idja=$_GET['idja'];
//Récupération de l'identifiant de l'utilisateur connecté   
    $id=$_SESSION['utilisateur'][2];
//Requête d'insertion de la table apprécier de l'identifiant du membre et du commentaire
    $sql="Insert into Apprecier(idCom, idM)".
      "values('$idja','$id')";
    $pub=mysqli_query($co,$sql);
     
//récupération du lien de la page d'où l'on vient   
    $lien=$_SERVER["HTTP_REFERER"];
//retour sur la page précédente qui sera mise à jour.
    echo "<meta http-equiv='refresh' content='0; URL=$lien'>" ;
        


?>