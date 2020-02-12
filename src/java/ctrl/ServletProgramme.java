/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Profil;
import metier.Programme;

public class ServletProgramme extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        RequestDispatcher rd = request.getRequestDispatcher("programme"); // je récupère mon dispatche

        List<Programme> queryProgramme = session.createCriteria(Programme.class)
                .list();
        List<Profil> queryProfil = session.createCriteria(Profil.class)
                .list();        
        List<Object[]> querySeanceBilan = session.createQuery("select s.idSeance, s.nomSeance, s.typeSeance, s.difficulte.nomDifficulte from  Seance s where s.typeSeance= \"Bilan\"").list();
        List<Object[]> querySeanceStand = session.createQuery("select s.idSeance, s.nomSeance, s.typeSeance, s.difficulte.nomDifficulte from  Seance s where s.typeSeance<>\"Bilan\"").list();
        
        
        request.setAttribute("programme", queryProgramme); // Je mets mon arrayList "list" en tant qu'attribut du request
        request.setAttribute("profil", queryProfil); // Je mets mon arrayList "list" en tant qu'attribut du request
        request.setAttribute("seancebilan", querySeanceBilan); 
        request.setAttribute("seancestand", querySeanceStand); 
        session.close();
        rd.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
