/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Exercice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author ptitp
 */
public class ServletListeExercices extends HttpServlet {

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
response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter out = response.getWriter()) 
                {
                out.println("<?xml version=\"1.0\"?>");
                    /*----- Ouverture de la session et de la transaction -----*/
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction t = session.beginTransaction();

                String caractere = request.getParameter("id");
                
                try {
                   
                    List<Exercice> exercices = session.createQuery("select new metier.Exercice(ex.idExercice, ex.nomExercice, ex.imageExercice) "+
                                                                    "from Exercice ex, AvoirCategorieExercice av "+
                                                                    "where av.idExercice=ex.idExercice and av.idCategorieExercice = :para").setParameter("para", caractere).list();     
                    for(Exercice ex : exercices){
                        out.println("<element>");
                            out.println("<id>"+ex.getIdExercice()+"</id>");
                            out.println("<name>"+ex.getNomExercice()+"</name>");
                            out.println("<image>"+ex.getImageExercice()+"</image>");
                        out.println("</element>");
                    } 

                    
                }catch(Exception ex) {
                    out.println(ex.getMessage());
                }

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
