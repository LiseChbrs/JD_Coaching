/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CategorieExercice;
import metier.Exercice;
import metier.ObjectifExercice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import orm.HibernateUtil;


public class ServletCreationExercice extends HttpServlet{



	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
    
                        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                        Transaction t = session.beginTransaction();

                        RequestDispatcher rd = request.getRequestDispatcher("exercice"); // je récupère mon dispatche


                        List<CategorieExercice> categories = session.createCriteria(CategorieExercice.class)
                            .list();

                        List<ObjectifExercice> objectifs = session.createCriteria(ObjectifExercice.class)
                       .list();

                        request.setAttribute("categories", categories); // Je met mon arrayListe "list" en tant qu'attribut du reques

                        request.setAttribute("objectifs", objectifs); // Je met mon arrayListe "list" en tant qu'attribut du request
                        rd.forward(request, response); // j'envoi le request ?

                    
                    }
                    
                    

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }
}
    
    
    