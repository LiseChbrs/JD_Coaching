/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static java.util.stream.DoubleStream.builder;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Exercice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import orm.HibernateUtil;

/**
 *
 * @author Administrateur
 */
public class ServletVerifExercice extends HttpServlet {

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

                String caractere = request.getParameter("caractere");
                
                try {
                   
                    List<Object[]> exercices = session.createQuery("select ex.nomExercice from Exercice ex where ex.nomExercice = :para").setParameter("para", caractere).list();     
                    
                    if (exercices.isEmpty()) {
                        out.println("<element>true</element>"); 
                    }else {
                        out.println("<element>Attention, le nom de l'exercice existe</element>");
                    }
                    
                }catch(Exception ex) {
                    out.println("jpp");
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
