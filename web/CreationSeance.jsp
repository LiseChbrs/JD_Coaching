<%-- 
    Document   : CreationSeance
    Created on : 8 fevr. 2020, 18:21:26
    Author     : alied
--%>


<%@page import="metier.Seance"%>
<%@page import="metier.ContenirExercice"%>
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

        <style type="text/css">
           input[type=text]{
               border:2px solid #456879;
               border-radius:10px;
               height: 30px;
               width: 200px;
           }
           input[type=button]{
               border:2px solid #456879;
               
               border-radius:10px;
               height: 30px;
               width: 150px;
           }
            
               input[type=submit]{
               border:2px solid #456879;
               
               border-radius:10px;
               height: 30px;
               width: 150px;
           }
            
               input[type=number]{
               border:1px solid #456879;
               
               border-radius:10px;
               height: 30px;
               width: 190px;
           }
           
            
           div#cssexo {
                
           border-radius: 25px;
           background-color:#d4dcda;
           margin-left: auto;
           margin-right: auto;
           width: 50em
           }
            
           div#exoselect {
           border-radius: 25px;
           background-color:white;
           margin-left: auto;
           margin-right: auto;
           
           }


        </style>

    </head>

    <body>
        <% session.setAttribute("listeCE", new ArrayList<ContenirExercice>()); %>
        
        
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
            <div class="box" style="height :1200px;"  >
                <div class="col-lg-12">

                    <hr class="visible-xs">
                    <hr class="visible-xs">

                    <%--
                    Rechercher une séance avec AJAX
                    --%>
                    <hr>
                    <h2 class="intro-text text-center"><strong>Recherche d'une
                            séance</strong>
                    </h2>
                    <hr>
                    <div align="center" id="btnorange">
                    <input type="button" class="btn btn-warning" onclick="creerseance('#idNomSeance')" value="Cr&eacute;er une s&eacute;ance"/>
                    </div> </br>
                    <section style=width:100%;float:left;>
                        <div align="center" id="reseance">
                            <input type="text" id="rechercherSeance" onkeyup="RechercherSeance()" placeholder="Rechercher des séances..." >
                        </div>

                        <ul id="listeSeance">

                            <div style=width:100%;>

                                <%List<Seance> lstSeance = (List<Seance>) request.getAttribute("listeSeance");

                                    for (int i = 0; i < lstSeance.size(); i++) {
                                        Seance s = (Seance) lstSeance.get(i);

                                        out.println("<div class=\"card\" style=\"width:22em;float:left;margin:2%;\">");
                                        out.println("<div class=\"card-header\" id=\"headingOne\">");
                                        out.println(" <li class=\"list-group-item\"><button style=\"width: 100%;background-color:white;\" id= " + s.getIdSeance() + " class=\"btn btn-outline-warning\" type=\"button\" "
                                                + "data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" "
                                                + "aria-controls=\"collapseOne\" > Seance " + s.getIdSeance() + " : " + s.getNomSeance() + " </button></li>");
                                        out.println("</div>");
                                        out.println("<div id=0" + s.getIdSeance() + " style =\"display: none\">");
                                        out.println("</div></div>");
                                    }

                                %>
                            </div>
                        </ul>
                    </section>

                    <hr class="visible-xs">
                    <hr class="visible-xs">

                    <%--
                    Rechercher une séance avec AJAX
                    --%>
                    <hr>
                    
                    <h2 class="intro-text text-center"><strong>Création d'une
                            séance</strong>
                    </h2>
                    <hr>

                    <form method="GET" action="ServletEnregistrementSeance">


                        <div  id="partigauche" style="height : 150px; width :250px; margin-left: 27%; float:left;  padding-top: 25px;">

                            Nom : <input type="text" name="nomSeance" id="idNomSeance" required /></br>
                            <input type="radio"  value="Basic" name="typeSeance" class="1" id="typeSeanceCache" a="1" checked style="display:none;"/></br>
                            Séance bilan : <input type="radio" name="typeSeance" value="Bilan" id="typeSeanceVisible" checked="false" class="1"/></br>
                            <input type="hidden" id="cacher" name="" value="">
                            Difficulté : <select name="difficulte" disabled ="true" id="selectDifficulte">
                                <option value="0" ></option>
                                <%  List<Difficulte> lstDifficulte = (List<Difficulte>) request.getAttribute("listeDifficulte");
                                    for (Difficulte d : lstDifficulte) {
                                        out.println("<option value=\"" + d.getIdDifficulte() + "\">");
                                        out.println(d.getNomDifficulte());
                                        out.println("</option>");
                                    }
                                %>
                            </select>

                        </div>

                        <div  style="height : 150px; width :300px; float:right; margin-right: 22%; " id="partiright">

                            <%  List<CategorieSeance> categories = (List<CategorieSeance>) request.getAttribute("listeCategorie");

                                //creation tab
                                out.println("<div style=\" \"><h2 class=\"intro-text\"><strong>Catégories séances :</strong></h2> ");

                                out.println("<select id=\"categorie1\" name=\"categorie\" multiple required >");
                                for (CategorieSeance cs : categories) {
                                    out.println("<option value=" + cs.getIdCategorieSeance() + ">" + cs.getNomCategorieSeance() + "</option>");
                                }
                                out.println("</select>");
                                out.println("</div >");
                                //creation tab

                            %>

                        </div>

                        <div align="center" id="partibas" style="  padding-top: 180px; ">

                            <h3 class="intro-text text-center">
                                <strong>Catégories d'exercices :</strong>
                            </h3>

                            <div align="center" id="listCatEx">

                                <% List<CategorieExercice> lstCategorieExercice = (List<CategorieExercice>) request.getAttribute("listeCategorieExercice");
                                    for (CategorieExercice ce : lstCategorieExercice) {
                                        if (ce.getNomCategorieExercice().equals("Echauffement")) {
                                            out.print("<input type=\"button\" id=" + ce.getIdCategorieExercice() + " style=\" display:block;\" onClick='afficherExercice(this.id)' value=" + ce.getNomCategorieExercice() + " name=\"buttalpha\">");
                                        } else {
                                            out.print("<input type=\"button\" id=" + ce.getIdCategorieExercice() + " style=\" display:none;\" onClick='afficherExercice(this.id)' value=" + ce.getNomCategorieExercice() + " name=\"buttalpha\">");
                                        }
                                    }

                                %>
                            </div>





                            <div id="cssexo">
                                <div id="cssexo2">
                                    <h3 class="intro-text text-center">
                                        <strong>Exercices disponibles :</strong>
                                    </h3>
                                    <div align="center" id="exodispo">
                                        <span  id="zoneExercice"></span>
                                    </div>

                                    <div align="center" id="exoselect"> <h2 class="intro-text text-center"><strong>Exercices selectionés :</strong></h2>
                                        <div  id="global"><span id="gauche"></span><span id="centrale"></span><span id="droite"></span></div>
                                    </div>
                                    <input type="hidden" value="" name="imgSession" id="cpt"/>

                                </div>
                            </div>
                            </br><input type="submit" id="btnEnregistrerSeance" disabled="disabled" value="Enregistrer la séance"/>
                    </form>
                </div>





            </div>
        </div>
    </div>


    <!-- /.container -->

    <div class="col-lg-12 text-center">
        <p>Copyright &copy; Your Website 2020</p>
    </div>



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

