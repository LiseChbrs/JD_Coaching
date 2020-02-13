<%-- 
    Document   : CreationSeance
    Created on : 8 fevr. 2020, 18:21:26
    Author     : alied
--%>

<%@page import="metier.Seance"%>
<%@page import="metier.SessionClassement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="metier.CategorieExercice"%>
<%@page import="metier.Difficulte"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="metier.CategorieSeance"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Saisie d'une séance">
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

    </head>

    <body>
        <% session.setAttribute("listeCE", new ArrayList<SessionClassement>()); %>


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
                            <a href="index.html">Accueil</a>
                        </li>
                        <li>
                            <a href="ServletCreationExercice">Exercice</a>
                        </li>
                        <li>
                            <a href="ServletCreationSeance?action=versForm">Séance</a>
                        </li>
                        <li>
                            <a href="ServletProgramme">Programme</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>



        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Création d'une
                        <strong>séance</strong>
                    </h2>
                    <hr>
                    <hr class="visible-xs">
                    <form method="GET" action="ServletCreationSeance">
                        Nom : <input type="text" name="nomSeance" id="idNomSeance"/>
                        Séance bilan <input type="checkbox" name="typeSeance" id="idTypeSeance"/> 
                        Difficulté : Catégorie : <select name="categorie" >
                            <option></option>
                            <%  List<Difficulte> lstDifficulte = (List<Difficulte>) request.getAttribute("listeDifficulte");
                                for (Difficulte d : lstDifficulte) {
                                    out.println("<option>");
                                    out.println(d.getNomDifficulte());
                                    out.println("</option>");
                                }
                            %>
                        </select>
                        <p>Catégorie : <select name="categorie" >
                                <option></option>
                                <%  List<CategorieSeance> lstCategorie = (List<CategorieSeance>) request.getAttribute("listeCategorie");
                                    for (CategorieSeance cs : lstCategorie) {
                                        out.println("<option>");
                                        out.println(cs.getNomCategorieSeance());
                                        out.println("</option>");
                                    }
                                %>
                            </select>
                            <input type="button" value="Enregistrer la categorie"/>     </P>    

                        Exercice :</br>
                        Categorie de l'exercice : 
                        <% List<CategorieExercice> lstCategorieExercice = (List<CategorieExercice>) request.getAttribute("listeCategorieExercice");
                            for (CategorieExercice ce : lstCategorieExercice) {
                                out.print("<input type=\"button\" id=" + ce.getIdCategorieExercice() + " onClick='afficherExercice(this.id)' value=" + ce.getNomCategorieExercice() + ">");
                            }

                        %>
                        <span id="zoneExercice"></span>
                        </br><input type="submit" value="Enregistrer la séance"/>
                    </form>
                    <hr class="visible-xs">

                    <%--
                    Rechercher une séance avec AJAX
                    --%>
                    <hr>
                    <h2 class="intro-text text-center">Recherche d'une
                        <strong>séance</strong>
                    </h2>
                    <hr>

                    <input type="text" id="rechercherSeance" onkeyup="RechercherSeance()" placeholder="Rechercher des séances..." >
                    <ul id="listeSeance">
                        <%List<Seance> lstSeance = (List<Seance>) request.getAttribute("listeSeance");


                            for (int i = 0; i < lstSeance.size(); i++) {
                                Seance s = (Seance) lstSeance.get(i);

                                out.println("<div class=\"card\" style=\"width: 20%;float=\"left\";\">");
                                out.println("<div class=\"card-header\" id=\"headingOne\">");
                                out.println(" <li class=\"list-group-item\"><button id= " + s.getIdSeance() + " class=\"btn btn-outline-warning\" type=\"button\" "
                                        + "data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" "
                                        + "aria-controls=\"collapseOne\" > Seance " + s.getIdSeance() + " : " + s.getNomSeance() + " </button></li>");
                                out.println("</div>");
                                out.println("<div id=0" +s.getIdSeance() +" style =\"display: none\">");
                                out.println("</div></div>");
                            }

                       %>
                    </ul>

                </div>
            </div>
        </div>

        <!-- /.container -->

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <p>Copyright &copy; Your Website 2020</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="boots/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="boots/js/bootstrap.min.js"></script>

    <script type="text/JavaScript" src="jsfonction/afficheExercice.js"></script>
    <script type="text/JavaScript" src="jsfonction/afficherSeance.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="boots/js/bootstrap.min.js"></script>
</body>

</html>

