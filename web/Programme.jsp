<%@page import="metier.ContenirSeance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="metier.Difficulte"%>
<%@page import="metier.CategorieSeance"%>
<%@page import="metier.Seance"%>
<%@page import="metier.Profil"%>
<%@page import="metier.Programme"%>
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
        <meta name="description" content="Saisie d'une s�ance">
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
                            <a href="ServletCreationSeance">S�ance</a>
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

        <div class="container">

            <div class="row">
                <div class="box">
                    <div class="col-lg-12 text-center">
                        <h2>Programmes</h2></br></br>

                        <section style=width:100%;float:left;>
                            <input type="text" id="rechercherProg" onkeyup="RechercherProgramme()" placeholder="Rechercher des programmes..." style=width:50%; >
                            <ul id="listeProg">
                                <div style=width:100%;>
                                    <%
                                        List<Programme> prog = (List<Programme>) request.getAttribute("programme");
                                        for (int i = 0; i < prog.size(); i++) {
                                            Programme p = (Programme) prog.get(i);

                                            out.println("<div class=\"card\" style=\"width:22em;float:left;margin:2%;\">");
                                            out.println("<div class=\"card-header\" id=\"headingOne\">");
                                            out.println(" <li class=\"list-group-item\"><button style=\"width: 100%;background-color:white;\" name=" + p.getIdProgramme() + " id=\"prog\" class=\"btn btn-outline-warning\" type=\"button\" "
                                                    + "data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"false\" "
                                                    + "aria-controls=\"collapseOne\">" + p.getNomProgramme() + "</li>");
                                            out.println("</button></div>");
                                            out.println("<div id=" + p.getIdProgramme() + " style =\"display: none\">");
                                            out.println("</div>");
                                            out.println("</div>");
                                        }
                                    %>
                                </div>
                            </ul>
                        </section>
                    </div>

                    <h2>Cr�ation de programme</h2>

                    <%
                        HttpSession sessionHttp = request.getSession();
                        ArrayList<ContenirSeance> listeCS = new ArrayList<ContenirSeance>();
                        sessionHttp.setAttribute("listeCS", listeCS);
                    %>

                    <form action="ServletEnregistrementProgramme" method="GET" >
                        <input type="text" required name="nomProg" id="nomProg" placeholder="Rentrer un nom de programme" />       


                        <% List<Profil> listeProfil = (List<Profil>) request.getAttribute("profil");
                            sessionHttp.setAttribute("listeProfil", listeProfil);
                            out.println("<input list=\"listeProgramme\" name=\"monProg\" required>");
                            out.println("<datalist id=\"listeProgramme\">");

                            for (int i = 0; i < listeProfil.size(); i++) {
                                out.println("<option value=\"" + listeProfil.get(i).getNomProfil() + "\">" + listeProfil.get(i).getNomProfil() + "</option>");
                            }
                            out.println("</datalist>");

                        %>

                        <h3>S�lectionnez vos s�ances</h3>

                        <input type="button" id="btnTypeBilan" value="Bilan" style="display: inline;" name="btnTypeProgramme" onclick="afficherSeanceBilan()"/>




                        <input type="button" id="btnTypeStandard" value="Standard" style="display: none;" name="btnTypeProgramme" onclick="afficherSeanceStandard()"/>
                        </br>

                        <div id="champsProgrammes"></div>
                        <input type="submit" id="btnEnregistrer" style="display:none;"value="Enregistrer le programme" /> 
                        <div id="sessionProgrammes"></div>
                        <input type="hidden" id="valeurCachee" value="0" />
                    </form>

                    <%--  
                   <form method="GET" action="ServletEnregistrementSeance">
                       Nom : <input type="text" name="nomSeance" id="idNomSeance"/>
                       S�ance bilan <input type="radio" style="display: inline;" value="Basic" name="typeSeance" id="typeSeanceCache" checked="true" />
                       <input type="radio" name="typeSeance" value="Bilan" id="typeSeanceVisible" checked="false" onclick="switchRadio()"/> 
                       Difficult� : <select name="difficulte" id="selectDifficulte">
                           <option value="0"></option>
                           <%  List<Difficulte> lstDifficulte = (List<Difficulte>) request.getAttribute("listeDifficulte");
                               for (Difficulte d : lstDifficulte) {
                                   out.println("<option value=\"" + d.getIdDifficulte() + "\">");
                                   out.println(d.getNomDifficulte());
                                   out.println("</option>");
                               }
                           %>
                       </select>
                       <p>Cat�gorie : <select name="categorie" >
                               <%  List<CategorieSeance> lstCategorie = (List<CategorieSeance>) request.getAttribute("listeCategorie");
                                   for (CategorieSeance cs : lstCategorie) {
                                       out.println("<option value=\"" + cs.getIdCategorieSeance() + "\">");
                                       out.println(cs.getNomCategorieSeance());
                                       out.println("</option>");
                                   }
                               %>
                           </select>
                           <input type="button" value="Enregistrer la categorie"/>     </P>    

                        </br>
                        Categorie de l'exercice : <div id="listCatEx">
                            <% List<CategorieExercice> lstCategorieExercice = (List<CategorieExercice>) request.getAttribute("listeCategorieExercice");
                                for (CategorieExercice ce : lstCategorieExercice) {
                                    if (ce.getNomCategorieExercice().equals("Echauffement")) {
                                        out.print("<input type=\"button\" id=" + ce.getIdCategorieExercice() + " style=\" display:block;\" onClick='afficherExercice(this.id)' value=" + ce.getNomCategorieExercice() + ">");
                                    } else {
                                        out.print("<input type=\"button\" id=" + ce.getIdCategorieExercice() + " style=\" display:none;\" onClick='afficherExercice(this.id)' value=" + ce.getNomCategorieExercice() + ">");
                                    }
                                }

                            %>
                        </div>
                        Exercice :</br>
                        <span id="zoneExercice"></span>


                        <div  id="global"><span id="gauche"></span><span id="centrale"></span><span id="droite"></span></div>

                        <input type="hidden" value="" name="imgSession" id="cpt"/>



                        </br><input type="submit" id="btnEnregistrerSeance" disabled="disabled" value="Enregistrer la s�ance"/>
                    </form>
                    </div>
                    --%>


                    <!-- /.container -->

                </div>
            </div>
            <!-- /.footer -->
            <footer>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <p>Copyright &copy; Your Website 2020</p>
                        </div>
                    </div>


                </div>



            </footer>

            <div class="col-lg-12 text-center">
                <p>Copyright &copy; Your Website 2020</p>
            </div>


            <!-- jQuery -->
            <script src="boots/js/jquery.js"></script>
            <script src="boots/js/bootstrap.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="boots/js/bootstrap.min.js"></script>


            <style type="text/css">

                .button {border-radius: 2px;
                         background-color: #555555;
                }
            </style>
            <script type="text/JavaScript" src="jsfonction/pageafficheprog.js"></script>
</html>