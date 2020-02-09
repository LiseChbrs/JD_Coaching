/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CategorieExercice;
import metier.ObjectifExercice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import orm.HibernateUtil;

/**
 *
 * @author alied
 */
public class ServletCreationExercice extends HttpServlet {

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
        
System.out.println("test");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        RequestDispatcher rd = request.getRequestDispatcher("exercice"); // je récupère mon dispatche

        List categories = session.createCriteria(CategorieExercice.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("idCategorieExercice"))
                        .add(Projections.property("nomCategorieExercice")))
                .list();

        List objectifs = session.createCriteria(ObjectifExercice.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("idObjectifExercice"))
                        .add(Projections.property("descriptionObjectifExercice")))
                .list();

        request.setAttribute("categories", categories); // Je met mon arrayListe "list" en tant qu'attribut du reques

        request.setAttribute("objectifs", objectifs); // Je met mon arrayListe "list" en tant qu'attribut du request
        try {
            rd.forward(request, response); // j'envoi le request ?
        } catch (ServletException ex) {
            Logger.getLogger(ServletCreationExercice.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("kek");
        } catch (IOException ex) {
            Logger.getLogger(ServletCreationExercice.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("ika");

        }
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
