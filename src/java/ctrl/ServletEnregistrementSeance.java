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
import metier.Difficulte;
import metier.Exercice;
import metier.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author ptitp
 * Cette servlet permet d'enregistrer la séance en base ainsi que les catégories
 * de séance et les exercices qui la concernent.
 */
public class ServletEnregistrementSeance extends HttpServlet {

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
        ArrayList<ContenirExercice> listeCE = (ArrayList<ContenirExercice>) sessionHttp.getAttribute("listeCE");
        
        String nomSeance= request.getParameter("nomSeance");
        String typeSeance = request.getParameter("typeSeance");
        String[] categorie= request.getParameterValues("categorie");
        int difficulte= Integer.valueOf(request.getParameter("difficulte"));
        
        //On crée l'objet Séance et on le save pour l'enregistrer en base.
        Seance s ;
        if(difficulte == 0){
            s= new Seance(nomSeance, typeSeance);
            session.save(s);
        } else {
            Difficulte d =(Difficulte)session.get(Difficulte.class, difficulte);
            s= new Seance(d,nomSeance, typeSeance );
            session.save(s);
        }
        
        //On ajoute les Catégories de Seance à la séance.
        for(String el: categorie){
            CategorieSeance cs =(CategorieSeance)session.get(CategorieSeance.class, Integer.valueOf(el));
            s.addCategorieSeance(cs);
        }
        
        //On remplit les informations incomplètes et on save.
        for(int i = 0; i < listeCE.size(); i++){
            ContenirExercice ce = listeCE.get(i);
            ce.setSeance(s);
            int idEx = ce.getExercice().getIdExercice();
            int idS = s.getIdSeance();
            if(i == 0){
                ce.setId(new ContenirExerciceId(idEx, idS, 1));
            } else if( i == 1){
                ce.setId(new ContenirExerciceId(idEx, idS, listeCE.size()));
            } else {
                ce.setId(new ContenirExerciceId(idEx, idS, i));
            }
            
            session.save(ce);
        }
        
        //On finit par tout commit.
        t.commit();
        sessionHttp.removeAttribute("listeCE");
        
        RequestDispatcher rd = request.getRequestDispatcher("ServletCreationSeance");
        rd.forward(request, response);
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
