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

        <!-- lien pour le logo de notre site  -->
        <div class="brand" ><p><img src="boots/image/logojd.jpg"  alt="logojd" width="150" height="150"  ></p>JD Coaching</div>
        <div class="address-bar">Coach sportif dipl�m�</div>
        
        
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
                            <a href="index.html">Accueil</a>
                        </li>
                        <li>
                            <a href="Exercice.jsp">Exercice</a>
                        </li>
                        <li>
                            <a href="CreationSeance.jsp">S�ance</a>
                        </li>
                        <li>
                            <a href="Programme.jsp">Programme</a>
                        </li>
                    </ul>
                </div>
               
            </div>
            
        </nav>

        <div class="container">

            <div class="row">
                <div class="box">
                    <div class="col-lg-12 text-center">
                        <div id="carousel-example-generic" class="carousel slide">


                            
                            <!-- Le formulaire de cr�ation d'exercice -->

                            <!-- Wrapper for slides -->

                            <h2>Entrez les champs suivants</h2></br></br>
                            <form action="ServletCreationExercice">
                                <input type="text" id="zone" name="nom" value="" placeholder="Nom" required/><br><br>
                                <div id="resultat"></div>
                                <input type="text" name="description" placeholder="Mettez une description (optionnel)" value="" size="25" /></br><br>
                                <input type="text" name="lienimg" value="" placeholder="rentrer l'URL de l'image" /><br><br>
                                <input type="text" name="lienvid" value="" placeholder="rentrer l'URL de la vido"/><br><br>   
                                
                                <!-- Lecture de la BDD afin de r�cup�rer les cat�gories et les objectifs  -->
                                <%

                                    //r�cup attribut
                                    List<CategorieExercice> categories = (List<CategorieExercice>) request.getAttribute("categories");
                                    List<ObjectifExercice> objectifs = (List<ObjectifExercice>) request.getAttribute("objectifs");

                //creation tab
           out.println("<h1> Categories </h1> ");
                for(int i=0; i<categories.size();i++){
                    CategorieExercice ce =(CategorieExercice)categories.get(i);
                    out.println("<input type=\"checkbox\" name =\"cat\" value="+ce.getIdCategorieExercice()+">"+ce.getNomCategorieExercice() + "required");
                          
                }out.println("</br>");
                
				
                //creation tab
                 out.println("<h1> Objectifs </h1> ");
                for(int i=0; i<objectifs.size();i++){
                    ObjectifExercice ob =(ObjectifExercice)objectifs.get(i);
                   out.println("<input type=\"checkbox\" name =\"obj\"  value="+ob.getIdObjectifExercice()+">"+ob.getNomObjectifExercice() + "required");
                          
                }out.println("</br>");
                
                
                    
                

                                    //creation tab
                                    out.println("<h1> Categories </h1> ");
                                    out.println("<select id=\"catego\" name=\"cat\" multiple >");
                                    for (int i = 0; i < categories.size(); i++) {
                                        CategorieExercice ce = (CategorieExercice) categories.get(i);
                                        out.println("<option value=" + ce.getIdCategorieExercice() + ">" + ce.getNomCategorieExercice() + "</option>");

                                    }
                                    out.println("</select>");

                                    out.println("</br>");

                                    //creation tab
                                    out.println("<h1> Objectifs </h1> ");
                                    out.println("<select id=\"object\" multiple name=\"obj\" >");

                                    for (int i = 0; i < objectifs.size(); i++) {
                                        ObjectifExercice ob = (ObjectifExercice) objectifs.get(i);
                                        out.println("<option value=" + ob.getIdObjectifExercice() + ">" + ob.getNomObjectifExercice() + "</option>");

                                    }

                                    out.println("</select>");
                                    out.println("</br>");

 
                                    //message d'info sur l'ajout
                                    String msg_info = (String)request.getAttribute("msg");
                                    if (msg_info != null)

                                    out.println("<p><strong> <font color=\"green\"></font>" + msg_info + "</strong></p>");
 %>                                    

                                
                                <input type="submit" name="btnajouter" value="Enregistrer" id="btnajouter" />
                            </form>    


                        </div>

                    </div>
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
        </footer>

        <!-- jQuery -->
        <script src="boots/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="boots/js/bootstrap.min.js"></script>

        <script type="text/JavaScript" src="jsfonction/ajax.js"></script>


    </body>

</html>