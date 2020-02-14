/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.CategorieSeance;
import metier.ContenirExercice;
import metier.ContenirExerciceId;
import metier.ContenirSeance;
import metier.ContenirSeanceId;
import metier.Difficulte;
import metier.Profil;
import metier.Programme;
import metier.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author ptitp
 */
public class ServletEnregistrementProgramme extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        HttpSession sessionHttp = request.getSession();
        ArrayList<ContenirSeance> listeCS = (ArrayList<ContenirSeance>) sessionHttp.getAttribute("listeCS");
        ArrayList<Profil> listeProfil = (ArrayList<Profil>) sessionHttp.getAttribute("listeProfil");
        
        String nomProg= request.getParameter("nomProg");
        String monProg = request.getParameter("monProg");
        
        int idProf = 0; 
        for(int i =0; i < listeProfil.size(); i++){
            if(listeProfil.get(i).getNomProfil() == monProg){
                idProf = listeProfil.get(i).getIdProfil();
                break;
            }
        }
        Profil prof;
        if(idProf != 0){
            prof = (Profil)session.get(Profil.class, idProf);
        } else {
            prof = new Profil(monProg);
            session.save(prof);
        }
        
        //On crée l'objet Séance et on le save pour l'enregistrer en base.
        Programme prog = new Programme(prof, nomProg);
        session.save(prog);
        
        int idProg = prog.getIdProgramme();
        
        //On remplit les informations incomplètes et on save.
        for(int i = 0; i < listeCS.size(); i++){
            ContenirSeance cs = listeCS.get(i);
            cs.setProgramme(prog);
            int idS= cs.getSeance().getIdSeance();
            cs.setId(new ContenirSeanceId(idS, idProg, i+1));
            session.save(cs);
        }
        
        //On finit par tout commit.
        t.commit();
        sessionHttp.removeAttribute("listeCS");
        response.sendRedirect("ServletProgramme");
   //     RequestDispatcher rd = request.getRequestDispatcher("ServletProgramme");
     //   rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
