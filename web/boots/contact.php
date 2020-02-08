<!--Page de contact des créateurs du site-->
<?php
session_start();
    //charger le fichier de fonctions
        require('fonctionsUtiles.php');
        //exécuter la fonction
        $co= connexion_choix();
?>
<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Ludovic Sentenac & Aliénor Roussel">

    <title>Contact - AliDo </title>

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

    <div class="brand">AliDo</div>
    <div class="address-bar">Système d'Information et Gestion des Entreprises
        <?php
if(isset($_SESSION['utilisateur'])){
    $pseudo=$_SESSION['utilisateur'][0];
    echo'/ Bonjour '.$pseudo.' !';
} ?>
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
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Contacter
                        <strong>AliDo</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-md-8">
                    <!-- Embedded Google Map using an iframe - to select your location find it on Google maps and paste the link as the iframe src. If you want to use the Google Maps API instead then have at it! -->
                    <iframe width="100%" height="400" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?hl=en&amp;ie=UTF8&amp;ll=137.0625,-95.677068&amp;spn=156.506174,79.013672&amp;t=m&amp;z=4&amp;output=embed"></iframe>
                </div>
                <div class="col-md-4">
                  
                    <p>Concepteurs:
                        <strong>Ludovic Sentenac</strong> &
                         <strong>Aliénor Roussel</strong>
                    </p>
                    <p>Adresse:
                        <strong>1 Place du Capitole
                            <br>31000 Toulouse</strong>
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Formulaire
                        <strong>de contact</strong>
                    </h2>
                    <hr>                   
                    <form role="form" action="ludovic.sentenac@ut-capitole.fr">
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label>Nom *</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="form-group col-lg-4">
                                <label>Adresse email *</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group col-lg-4">
                                <label>Numéro de téléphone</label>
                                <input type="tel" class="form-control">
                            </div>
                            <div class="clearfix"></div>
                            <div class="form-group col-lg-12">
                                <label>Message</label>
                                <textarea class="form-control" rows="6"></textarea>
                            </div>
                            <div class="form-group col-lg-12">
                                <input type="hidden" name="save" value="contact">
                                <button type="submit" class="btn btn-default">Envoyer</button>
                            </div>
                        </div>
                    </form>
                </div>
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

</body>

</html>
