/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.servlet.http.HttpSession;
import metier.Difficulte;
import metier.Exercice;
import metier.Profil;
import metier.Programme;
import metier.Seance;
import org.hibernate.SQLQuery;

public class ServletProgramme extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        RequestDispatcher rd = request.getRequestDispatcher("programme"); // je récupère mon dispatche

        List<Programme> queryProgramme = session.createCriteria(Programme.class)
                .list();
        List<Profil> queryProfil = session.createCriteria(Profil.class)
                .list();

        SQLQuery qSBilan = session.createSQLQuery("select s.idSeance, s.nomSeance, s.typeSeance from  Seance s where s.typeSeance= \"Bilan\"");
        List l = qSBilan.list();
        Iterator it = l.iterator();
        ArrayList<Seance> listSeanceBilan = new ArrayList<Seance>();
        while (it.hasNext()) {
            Object[] objet = (Object[]) it.next();
            listSeanceBilan.add(new metier.Seance((Integer) objet[0], (String) objet[1], (String) objet[2]));
        }
        
        SQLQuery qSStand = session.createSQLQuery("select s.idSeance, s.nomSeance, s.typeSeance, s.idDifficulte from  Seance s where s.typeSeance<>\"Bilan\"");
        List l1 = qSStand.list();
        Iterator it1 = l1.iterator();
        ArrayList<Seance> listSeanceStand = new ArrayList<Seance>();
        while (it1.hasNext()) {
            Object[] objet = (Object[]) it1.next();
            int idD =  (Integer)objet[3];
            listSeanceStand.add(new metier.Seance((Integer) objet[0],  (Difficulte)session.get(Difficulte.class, idD ), (String) objet[1], (String) objet[2]));
        }
        request.setAttribute("programme", queryProgramme); // Je mets mon arrayList "list" en tant qu'attribut du request
        request.setAttribute("profil", queryProfil); // Je mets mon arrayList "list" en tant qu'attribut du request
        
        HttpSession sessionHttp = request.getSession();
        sessionHttp.setAttribute("seancebilan", listSeanceBilan); 
        sessionHttp.setAttribute("seancestand", listSeanceStand); 

        session.close();
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
