/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.ContenirExercice;
import metier.Exercice;
import metier.SessionClassement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author ptitp
 */
public class ServletEnregistrementSession extends HttpServlet {

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
 
        System.out.println("Ok!!!!");
        HttpSession sessionHttp = request.getSession();
        ArrayList<SessionClassement> listeCE = (ArrayList<SessionClassement>)sessionHttp.getAttribute("listeCE");
        int nboccurence = Integer.valueOf(request.getParameter("nboccurence"));
        Float tempspause = Float.valueOf(request.getParameter("tempspause"));
        int idEx = Integer.valueOf(request.getParameter("idEx"));
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Exercice ex = (Exercice)session.load(Exercice.class, idEx);
        

        SessionClassement ce;
        if (request.getParameter("nbrepetition").equals("")) {
            Float duree = Float.valueOf(request.getParameter("duree"));
            ce = new SessionClassement( ex,null ,nboccurence,tempspause,duree);
        //System.out.println(ce.toString());
        } else {
            int nbrepetition = Integer.valueOf(request.getParameter("nbrepetition"));
            ce = new SessionClassement( ex,nbrepetition ,nboccurence,tempspause,null);
        //System.out.println(ce.toString());
        } 
            
            listeCE.add(ce);
            sessionHttp.setAttribute("listeCE", ce);
            session.close();
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