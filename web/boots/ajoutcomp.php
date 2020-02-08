<?php session_start();
    //charger le fichier de fonctions
        require('fonctionsUtiles.php');
        //exécuter la fonction
        $co= connexion_choix(); ?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
//On récupere les données du membre connecté
    $pseudo=$_SESSION['utilisateur'][0];
    $nom=$_SESSION['utilisateur'][3];
    $prenom=$_SESSION['utilisateur'][4];
    $email=$_SESSION['utilisateur'][5];
    $id=$_SESSION['utilisateur'][2];
    $md=$_SESSION['utilisateur'][1];
//On récupere la compétence et le niveau à ajouter    
    $cc=$_GET["comp"];
    $nn=$_GET["niveau"];
//On regarde si la compétence est déjà possédée par le membre, et on l'ajoute si le membre ne possède pas encore cette compétence    

    $sqlaa="select p.idC from Posseder p, Competence c where p.idC = c.idC and c.nomC = '$cc' and p.idM = '$id'";
    $mmm= mysqli_query($co, $sqlaa);
    $ii= mysqli_num_rows($mmm);
    if($ii!=0)
    {echo "<meta http-equiv='refresh' content='0; URL=modifierdonnees.php'>" ;
        
    }
    else
    {
        $sqlc="select idC from Competence where nomC='$cc'";
        $cur=mysqli_query($co, $sqlc);
        $ipa=mysqli_fetch_array($cur);
        $idcc=$ipa['idC'];
        
            $sql="insert into Posseder (idC, idM, niveau)"." VALUES ('$idcc','$id','$nn')";
            $ajoutc= mysqli_query($co, $sql);
            echo "<meta http-equiv='refresh' content='0; URL=modifierdonnees.php'>" ;
    }
        ?>
    </body>
</html>
