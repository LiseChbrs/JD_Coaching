<%@page import="metier.Exercice"%>
<%@page import="org.hibernate.Session"%>
<%@page import="orm.HibernateUtil"%>
<%@page import="metier.CategorieExercice"%>
<%@page import="metier.ObjectifExercice"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Saisie d'une sï¿½ance">
        <meta name="author" content="JALA-PC">

        <title>JD Coaching</title>

        <!-- Bootstrap Core CSS -->
        <link href="boots/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="boots/business-casual.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <style type="text/css">
        input {
	border:2px solid #456879;
	border-radius:10px;
	height: 30px;
	width: 300px;
        }

        select {
        width : 15em; 
        }    
        </style>

    </head>

    <body>

<<<<<<< HEAD


        <!-- lien pour le logo de notre site  -->
=======
>>>>>>> master
        <!-- Les onglets de navigation  -->
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
                    <a class="navbar-brand" href="index.html">JD Coaching</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="index.html">JD Coaching</a>
                        </li>
                        <li>
                            <a href="ServletCreationExercice">Exercice</a>
                        </li>
                        <li>
<<<<<<< HEAD
                            <a href="ServletCreationSeance">Séance</a>
=======
                            <a href="ServletCreationSeance">Sï¿½ance</a>
>>>>>>> master
                        </li>
                        <li>
                            <a href="ServletProgramme">Programme</a>
                        </li>
                    </ul>
                </div>

            </div>

        </nav>

        <div class="container">

            <div class="row">
                <div class="box">
                    <div class="col-lg-12 text-center">
<<<<<<< HEAD
                            <!-- Le formulaire de création d'exercice -->
=======
                        <div id="carousel-example-generic" class="carousel slide">
                            <!-- Le formulaire de crï¿½ation d'exercice -->
>>>>>>> master

                            <!-- Wrapper for slides -->
                            <%
                                //message d'info sur l'ajout
                                String msg_info = (String) request.getAttribute("msg");
                                if (msg_info != null) {
                                    out.println("<p><strong> <font color=\"green\"></font>" + msg_info + "</strong></p>");
                                }
                            %>
                            <hr>
<<<<<<< HEAD
                            <h1>Exercices</h1>

                            <%--
                            
                            visualisation exercice
                            --%>
                            <section style=width:100%;float:left;>
                            <ul class="list-group list-group-horizontal" id="listeExo">
                                <% List<Exercice> lstExo = (List<Exercice>) request.getAttribute("exercices");

                                    for (int i = 0; i < lstExo.size(); i++) {
                                        Exercice e = (Exercice) lstExo.get(i);

                                        out.println("<div class=\"card\" style=\"width: 20em;float=left;margin:2%;display: inline-block;\">");
                                        out.println("<div class=\"card-header\" id=\"headingOne\" style=\"width:100%;\">");
                                        out.println("<li class=\"list-group-item\">" + e.getNomExercice() + " - " + "<IMG style=\"max-width: 100%;\" src=" + e.getImageExercice()
                                                + " border=\"0\" alt=\"Votre navigateur ne charge pas l'image.\">" + "</li>" );
                                        out.println("</div>");
                                        out.println("<div id=0" + e.getIdExercice() + " style =\"display: none\">");
                                        out.println("</div></div>");
                                    }

                                %>
                            </ul>       
                            </section>

                            <h1 >Création d'un
=======
                            <h1 >Crï¿½ation d'un
>>>>>>> master
                                <strong>exercice</strong>
                            </h1>
                            <hr>
                            
                            <form action="ServletAddExercice">
<<<<<<< HEAD
                                <input type="text" id="zone" name="nom" value="" placeholder="Nom de l'exercice" required/>
                                <input type="text" id="desc" name="description" placeholder="Description de l'exercice (optionnelle)" value="" size="40" /></br><br>
                                <input type="text" id="img" name="lienimg" value="" placeholder="Lien de l'image" required/>
                                <input type="text" id="vid" name="lienvid" value="" placeholder="Lien de la vidéo" /><br><br>   

                                <!-- Lecture de la BDD afin de rï¿½cupï¿½rer les catï¿½gories et les objectifs  --><div >
                                    <%                                        //rï¿½cup attribut
                                        List<CategorieExercice> categories = (List<CategorieExercice>) request.getAttribute("categories");
                                        List<ObjectifExercice> objectifs = (List<ObjectifExercice>) request.getAttribute("objectifs");

                                        //creation tab
                                        out.println("<div style=\"float:left;margin-left:25%;\"><h2> Categories </h2> ");

                                        out.println("<select id=\"catego\" name=\"cat\" multiple required >");
                                        for (int i = 0; i < categories.size(); i++) {
                                            CategorieExercice ce = (CategorieExercice) categories.get(i);
                                            out.println("<option value=" + ce.getIdCategorieExercice() + ">" + ce.getNomCategorieExercice() + "</option>");

                                        }
                                        out.println("</select>");
                                        out.println("</div >");
                                        out.println("<div  style=\"float:right;margin-right:25%;\">");
                                        //creation tab
                                        out.println("<h2> Objectifs </h2> ");
                                        out.println("<select id=\"object\" multiple name=\"obj\" required>");

                                        for (int i = 0; i < objectifs.size(); i++) {
                                            ObjectifExercice ob = (ObjectifExercice) objectifs.get(i);
                                            out.println("<option value=" + ob.getIdObjectifExercice() + ">" + ob.getNomObjectifExercice() + "</option>");

                                        }

                                        out.println("</select>");
                                        out.println("</div>");

                                        out.println("</br>");


                                    %> </div>     
                                </br> 
                                </br> 
                                </br> 
                                </br> 
                                </br> 
                                </br> 
                                </br> 
                                </br> 
=======
                                <input type="text" id="zone" name="nom" value="" placeholder="Nom de l'exercice" required/><br><br>
                                <div id="resultat"></div>
                                <input type="text" id="desc" name="description" placeholder="Description de l'exercice (optionnelle)" value="" size="40" /></br><br>
                                <input type="text" id="img" name="lienimg" value="" placeholder="Lien de l'image" required/><br><br>
                                <input type="text" id="vid" name="lienvid" value="" placeholder="Lien de la vidï¿½o" /><br><br>   

                                <!-- Lecture de la BDD afin de rï¿½cupï¿½rer les catï¿½gories et les objectifs  -->
                                <%
                                    //rï¿½cup attribut
                                    List<CategorieExercice> categories = (List<CategorieExercice>) request.getAttribute("categories");
                                    List<ObjectifExercice> objectifs = (List<ObjectifExercice>) request.getAttribute("objectifs");

                                    //creation tab
                                    out.println("<h2> Categories </h2> ");
                                    out.println("<select id=\"catego\" name=\"cat\" multiple required >");
                                    for (int i = 0; i < categories.size(); i++) {
                                        CategorieExercice ce = (CategorieExercice) categories.get(i);
                                        out.println("<option value=" + ce.getIdCategorieExercice() + ">" + ce.getNomCategorieExercice() + "</option>");

                                    }
                                    out.println("</select>");

                                    out.println("</br>");

                                    //creation tab
                                    out.println("<h2> Objectifs </h2> ");
                                    out.println("<select id=\"object\" multiple name=\"obj\" required>");

                                    for (int i = 0; i < objectifs.size(); i++) {
                                        ObjectifExercice ob = (ObjectifExercice) objectifs.get(i);
                                        out.println("<option value=" + ob.getIdObjectifExercice() + ">" + ob.getNomObjectifExercice() + "</option>");

                                    }

                                    out.println("</select>");
                                    out.println("</br>");


                                %>                                    
>>>>>>> master
                                <input type="button"  id="btnajouter"  name="btnajouter" value="Enregistrer"  />

                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" id ="popupctrl" style="display:none;"> 
                                </button>
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Vous allez ajouter un exercice</h5>
                                                <h6 class="modal-title" id="exampleModalLabel">Vous pouvez annuler en sortant de la fenetre</h6>
                                            </div>
                                            <div id="popup" class="modal-body">

                                            </div>
<<<<<<< HEAD
                                            <div class="modal-footer">
                                                <input type="submit" value="Enregistrer" >
=======
                                            <div class="btn button-5" style="background-color: white;
  color: black;
  border: 2px solid #555555;">
                                                <input type="submit" value="Enregistrer">
>>>>>>> master
                                            </div>
                                        </div>
                                    </div>
                                </div></form>    
<<<<<<< HEAD
                       
=======
                        </div>
>>>>>>> master
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container -->
  
          
                    <div class="col-lg-12 text-center">
                        <p>Copyright &copy; Your Website 2020</p>
                    </div>
               
        <style>
.button5 {
  background-color: white;
  color: black;
  border: 2px solid #555555;
}

.button5:hover {
  background-color: #555555;
  color: white;
}</style>

        <!-- jQuery -->
        <script src="boots/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="boots/js/bootstrap.min.js"></script>

        <script type="text/JavaScript" src="jsfonction/ajax.js"></script>
    </body>

</html>