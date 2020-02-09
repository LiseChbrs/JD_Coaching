
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Page d'accueil">
    <meta name="author" content="Ludovic Sentenac et Aliénor Roussel">

    <title>AliDo</title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
  <script language="JavaScript" type="text/javascript" src="fonctions.js"></script>
  <div class="brand">AliDo</div>
    <div class="address-bar">Systèmes d'Information et Gestion des Entreprises
    <?php
    //si une personne est connectée son pseudo s'affiche en haut de la page d'accueil
if(isset($_SESSION['utilisateur'])){
    $pseudo=$_SESSION['utilisateur'][0];
    echo'/ Bonjour '.$pseudo.' !';
}
?>
    </div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.php">AliDo</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <?php
//une personne est connectée sur le site                   
                    if(isset($_SESSION['utilisateur'])){
                        echo'<li>';
                        echo'<a href="index.php">Accueil</a>';
                        echo'</li>';
                        echo'<li>';
                        echo'<a href="monprofil.php">Mon Profil</a>';
                        echo'</li>';
                        echo'<li>';
                        echo'<a href="rechercher.php">Recherche</a>';
                        echo'</li>';
                        echo'<li>';
                        echo'<a href="contact.php">Contact</a>';
                        echo'</li>';
                        echo'<li>';
                        echo'<a href="deconnexion.php">Se déconnecter</a>';
                        echo'</li>';                   
                    }
//personne n'est encore connecté : la barre de menu est différente on ne peut accéder qu'à l'accueil et à la page contact                   
                    else{
                        echo'<li>';
                        echo'<a href="index.php">Accueil</a>';
                        echo'</li>';
                        echo'<li>';
                        echo'<a href="contact.php">Contact</a>';
                        echo'</li>';
                    }
                            ?>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
                   

                        <!-- Wrapper for slides -->
         <?php
//si personne n'est connecté la variable globale n'a pas été crée
//c'est une page de connexion qui s'affiche : il faut entre un pseudo et un mot de passe ou appuyer sur un lien pour s'inscrire
    if(!isset($_SESSION['utilisateur'])){
        echo'<div id="carousel-example-generic" class="carousel slide">';
        echo'<ol class="carousel-indicators hidden-xs">';
           echo' <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>';
            echo'<li data-target="#carousel-example-generic" data-slide-to="1"></li>';
            echo'<li data-target="#carousel-example-generic" data-slide-to="2"></li>';
        echo' </ol>';
            echo'<div class="carousel-inner">';
            echo'<div class="item active">';
                echo'<img class="img-responsive img-full" src="image/slide-3.jpg" alt="">';
            echo'</div>';
            echo'<div class="item">';
                echo'<img class="img-responsive img-full" src="image/ws1.jpg" alt="" >';
            echo'</div>';
            echo'<div class="item">';
               echo' <img class="img-responsive img-full" src="image/rt.jpg" alt="">';
            echo'</div>';
        echo'</div>';


        echo'<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">';
            echo'<span class="icon-prev"></span>';
        echo'</a>';
        echo'<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">';
           echo' <span class="icon-next"></span>';
        echo'</a>';
        echo'</div>';

        echo'<h2 class="brand-before">';
            echo'<small>Se connecter</small>';
        echo'</h2>';
                   
        
        echo'<h1>Identifiez vous</h1></br></br>';  
//Formulaire de connexion : pseudo et mot de passe
        echo'<form action="connecter.php" method="get">
                        
            <p>Pseudo :
            <input type="text" name="pseudo" id="pseudo" size="20" required /></br></br>
            Mot de passe : <input type="password" name="mdp" value="" size="20" required /></br></br>
            <input type="submit" value="Connexion" name="connexion" />
            </p>
            </form></br></br></div>';
//Pas encore membre, il faut cliquer sur l'hyperlien        
        echo'<a href ="inscription.php"> Pas encore membre? Inscrivez vous</a>';  
        echo'</div>';
        }
//Quelqu'un s'est connecté sur la page de connexion : une variable globale de l'utilisateur a été crée
//Page d'accueil        
    else{
//Formulaire qui permet de publier un message : envoie vers la page publier.php qui renvoie sur cette page        
            echo'<p>';
            echo'<form action = "publier.php">';
            echo'<textarea class="pub" name="com" value=""  maxlength="140" cols=50 rows=2 required></textarea>';
            echo' <input type="submit"  value="Publier" name="btnpub" />';
            echo'</form>';          
            echo' </p>';
            echo'<hr class="tagline-divider">';
//récupération du pseudo et de l'id de l'utilisateur actuellement connecté                       
          $pseudo=$_SESSION['utilisateur'][0];
          $id=$_SESSION['utilisateur'][2];
//Requête des commentaires principaux des gens que l'utilisateur suit ou les siens.         
          $sqlcomcon="select c.idM, c.contenu, c.date, m.pseudoM, c.idCom "
                  . " from Commentaire c, Membre m "
                  . " where c.idM=m.idM and idCom2 is null "
                  . " and c.idM IN (select idM2 from Suivre where idM='$id')"
                  . "union"
                  . "(select c.idM, c.contenu, c.date, m.pseudoM, c.idCom "
                  . " from Commentaire c, Membre m"
                  . " where c.idM=m.idM "
                  . " and c.idM = $id and idCom2 is null)"      
                  ;
//Requête des commentaires principaux des gens que l'utilisateur ne suit pas et qui ont été commentés.          
          $sql1="select c.idCom, c.contenu, m.pseudoM, c.date from Commentaire c, Membre m where "
                  . " m.idM=c.idM and c.idCom2 is null and c.idM<>$id and c.idCom in (select idCom2 from Commentaire)"
                  . " and c.idM not in (select idM2 from Suivre where idM=$id) group by c.idCom; ";
          $curseursql1=mysqli_query($co,$sql1);
          $miplet=mysqli_num_rows($curseursql1);
//Je retourne tous les identifiants des commentaires de la requête $sql1.
          for($m=0; $m<$miplet ; $m++){
              $mpart=mysqli_fetch_array($curseursql1);
              $idComm=$mpart['idCom'];
//A partir des résultats précédents je cherche les sous commentaires des gens que je suis qui ont commenté un commentaire principal de personne que je ne suis pas              
              $sql3="select c.idCom, c.contenu, m.pseudoM, c.idCom2, c.date from Commentaire c, Membre m where c.idM=m.idM"
                      . " and c.idCom2=$idComm and c.idM in (select idM2 from Suivre where idM=$id)";
              $curseursql2=mysqli_query($co,$sql3);
              $niplet=mysqli_num_rows($curseursql2);
//Je récupère tous les identifiants des commentaires principaux de personne que je ne suis pas qu'une des personnes que je suis à commenter              
              for($p=0; $p<$niplet;$p++){
                  $npart=mysqli_fetch_array($curseursql2);
                  $idbon=$npart['idCom2'];
//A partir de ces identifiants je récupère les commentaires principaux que j'affiche dans ma page d'accueil                  
                  $sql4="select c.contenu, c.date, m.pseudoM, c.idCom from Commentaire c, Membre m where c.idM=m.idM and c.idCom=$idbon";
                  $curseursql4=mysqli_query($co,$sql4);
                  $hiplet=mysqli_num_rows($curseursql4);
                  for($h=0;$h<$hiplet;$h++){
                    $hpart=mysqli_fetch_array($curseursql4);
                    echo'<div id="com">';
                    echo('<ul><p id="qui"><li>Par '.$hpart["pseudoM"].' le '.$hpart["date"].' :</p>');
                    echo('</li><li> '.$hpart["contenu"]);                          
                    echo"</li></ul>";  
                    $idCom2=$hpart['idCom'];
//hyperlien pour aimer un commentaire ou ne plus aimer                            
                    $sqlaime="select * from Apprecier a where a.idCom='$idComm' and a.idM='$id'";
                    $curseuraime=mysqli_query($co,$sqlaime);
                    $ipletaime=mysqli_num_rows($curseuraime);
                    if($ipletaime == 0){     
                            echo"<a href='aimer?idja=$idComm.php'> J'aime </a>";                               

                          }
                    else{
                            echo"<a href='desaimer?idja=$idComm.php' > Vous aimez </a>";

                          }
                    echo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//Hyperlien qui permet de commenter un commentaire : appel d'une fonction javascript qui me permet d'afficher une zone pour commenter ainsi qu'un bouton
//qui me renvoie vers la page recom.php                    
                    echo"<a onClick='apparition($p)'> Commenter </a>";

                    echo"<div id='quantite$p' style='display:none'>";
                    echo'<form name="recom" action="recom.php" >';?>
<!--Je mets dans un input caché l'identifiant du commentaire que je vais commenter pour l'envoyer sur la page recom.php                         -->
                    <input type="hidden" name="idco" value=<?php echo"$idComm"?>/>
                      <?php
                    echo'<textarea class="pub" name="com" value="" maxlength="140" cols=30 rows=2 required></textarea>';

                    echo'<input type="submit" value="Commenter" name="btnpub" />';
                    echo'</form>';
                    echo'</div>'; 
                    echo'</div>';
//Requête de sous commentaire : récupération de tous les sous commentaires ainsi que des sous commentaires de sous commentaires
                    $sqlcomcom=" select c.contenu,c.date, c.idCom,m.pseudoM,c.idM from Commentaire c, Membre m where m.idM=c.idM and idCom2=$idCom2"
                              . " union"
                              . " (select c.contenu,c.date, c.idCom,m.pseudoM, c.idM from Commentaire c, Membre m where c.idM=m.idM and c.idCom2 in (select c.idCom from Commentaire c where c.idCom2=$idCom2))";
                    $curseursous=mysqli_query($co,$sqlcomcom);
                    $zpletsous=mysqli_num_rows($curseursous);
//récupération et affichage de tous ces sous commentaires                    
                      for($z=0;$z<$zpletsous;$z++){
                        $zpart=mysqli_fetch_array($curseursous);
                            echo"<div id='recom'>";
                            echo('<ul><li>Par '.$zpart["pseudoM"].' le '.$zpart["date"].' :');
                            echo('</li><li> '.$zpart["contenu"]); 
                            echo"</li></ul>";
//hyperlien j'aime des sous commentaires                               
                                $idComcom=$zpart["idCom"];
                                $sqlaime2="select * from Apprecier a where a.idCom='$idComcom' and a.idM='$id'";
                                $curseuraime2=mysqli_query($co,$sqlaime2);
                                $ipletaime2=mysqli_num_rows($curseuraime2);
                                if($ipletaime2 == 0){     
                                    echo"<a href='aimer?idja=$idComcom.php'> J'aime </a>";                               

                                    }
                                else{
                                    echo"<a href='desaimer?idja=$idComcom.php' > Vous aimez</a>";
                                }
// hyperlien commenter des sous commentaires
                                $k=1;
                                echo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                                echo"<a onClick='apparitionsous($k$p$z)'> Commenter </a>";
                                echo"<div id='quantitesous$k$p$z' style='display:none'>";
                                echo'<form name="recom" action="recom.php" >';
                                echo'<textarea class="pub" name="com" value=""maxlength="140" cols=30 rows=2 required></textarea>';?>
                                <input type="hidden" name="idco" value=<?php echo"$idComcom"?>/>
                                <?php
                                echo'<input type="submit" value="Commenter" name="btnpub" />';
                                echo'</form>';
                                echo'</div>';  
                                echo'</div>';

                          }
                      }
                      echo '<hr class="tagline-divider">';
                      }

                  }
//Lancement de la requête de commentaire principaux des gens suivis et de ceux de l'utilisateur
      $curseur= mysqli_query($co, $sqlcomcon);
      if($curseur!= False ){
          $iplet=mysqli_num_rows($curseur);
          for($i=0 ; $i<$iplet; $i++){
              $ipart=mysqli_fetch_array($curseur);
              echo'<div id="com">';
              echo('<ul><p id="qui"><li>Par '.$ipart["pseudoM"].' le '.$ipart["date"].' :</p>');
              echo('</li><li> '.$ipart["contenu"]);                          
              echo"</li></ul>";  
              $idCom=$ipart["idCom"];
  //hyperlien aimer un commentaire ou déjà aimé                            
              $sqlaime="select * from Apprecier a where a.idCom='$idCom' and a.idM='$id'";
              $curseuraime=mysqli_query($co,$sqlaime);
              $ipletaime=mysqli_num_rows($curseuraime);
              if($ipletaime == 0){     
                echo"<a href='aimer?idja=$idCom.php'> J'aime </a>";                               

              }
              else{
                echo"<a href='desaimer?idja=$idCom.php' > Vous aimez </a>";

              }
              echo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
 //Hyperlien qui permet de commenter un commentaire                             
              echo"<a onClick='apparition($i)'> Commenter </a>";


              echo"<div id='quantite$i' style='display:none'>";
              echo'<form name="recom" action="recom.php" >';?>
              <input type="hidden" name="idco" value=<?php echo"$idCom"?>/>
                  <?php
              echo'<textarea class="pub" name="com" value="" maxlength="140" cols=30 rows=2 required></textarea>';

              echo'<input type="submit" value="Commenter" name="btnpub" />';
              echo'</form>';
              echo'</div>'; 
              echo'</div>';
//Je récupère l'identifiant du commentaire principal et l'identifiant du membre qui l'a publié                
              $idCom2=$ipart["idCom"];
              $idM=$ipart["idM"];
//Affichage des sous commentaire qui donnent réponses aux commentaires principaux                             
              $sqlcomcom=" select c.contenu,c.date, c.idCom,m.pseudoM,c.idM from Commentaire c, Membre m where m.idM=c.idM and idCom2=$idCom2"
                      . " union"
                      . " (select c.contenu,c.date, c.idCom,m.pseudoM, c.idM from Commentaire c, Membre m where c.idM=m.idM and c.idCom2 in (select c.idCom from Commentaire c where c.idCom2=$idCom2))";
              $curseur2=mysqli_query($co,$sqlcomcom);
              if($curseur2!=False){
                $iplet2=mysqli_num_rows($curseur2);
                for($j=0;$j<$iplet2;$j++){
                    $ipart2=mysqli_fetch_array($curseur2);
                    echo"<div id='recom'>";
                    echo('<ul><li>Par '.$ipart2["pseudoM"].' le '.$ipart2["date"].' :');
                    echo('</li><li> '.$ipart2["contenu"]); 
                    echo"</li></ul>";
//hyperlien j'aime des sous commentaires                               
                    $idComcom=$ipart2["idCom"];
                    $sqlaime2="select * from Apprecier a where a.idCom='$idComcom' and a.idM='$id'";
                    $curseuraime2=mysqli_query($co,$sqlaime2);
                    $ipletaime2=mysqli_num_rows($curseuraime2);
                    if($ipletaime2 == 0){     
                        echo"<a href='aimer?idja=$idComcom.php'> J'aime </a>";                               

                        }
                    else{
                        echo"<a href='desaimer?idja=$idComcom.php' > Vous aimez</a>";
                    }
// hyperlien commenter des sous commentaires
                    //création d'une variable $k car quand $i=0 ce n'est pas pris en compte
                    $k=1;
                    echo("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    echo"<a onClick='apparitionsous($k$i$j)'> Commenter </a>";
                    echo"<div id='quantitesous$k$i$j' style='display:none'>";
                    echo'<form name="recom" action="recom.php" >';
                    echo'<textarea class="pub" name="com" value=""maxlength="140" cols=30 rows=2 required></textarea>';?>
                    <input type="hidden" name="idco" value=<?php echo"$idComcom"?>/>
                    <?php
                    echo'<input type="submit" value="Commenter" name="btnpub" />';
                    echo'</form>';
                    echo'</div>';  
                    echo'</div>';

                              }
                echo '<hr class="tagline-divider">';
                              }
                              
                              
                              }
                      }

                    }
 
                              ?>
                      
<!--Css qui met en forme nos commentaire et nos sous commentaires               -->
                    <style type="text/css">
                   #recom{
                       border-radius:30px; 
                       width:50%; 
                       border-style:ridge;
                       margin-left:auto; 
                       margin-right:auto; 
                       border-color:#999999; 
                       padding-top:1%; 
                       padding-bottom:1%}
                   #qui{
                       font-weight:bold;
                       
                   }
                   #com
                   {border-radius:30px; 
                    width:70%; 
                    border-style:ridge; 
                    margin-left:auto; 
                    margin-right:auto;
                    border-color:#999999; 
                    padding-top:1%; 
                    padding-bottom:1%}
                   
                   #aime a:visited{
                       color:red;
                       text-decoration : blink;
                       text-transform: capitalize;
                   }
                   </style>
              
            </div>
        </div>
    </div>

        
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; Your Website 2019</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Permet d'initiialiser la vitesse de changement d'images sur la page de connexion -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
