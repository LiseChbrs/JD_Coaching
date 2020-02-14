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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author alied
 */
public class ServletSupDefPro extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");
            /*----- Ouverture de la session et de la transaction -----*/
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();

            String id = request.getParameter("id");

            try {

                int para = Integer.parseInt(id);

                //Exercice e = (Exercice) session.load(Exercice.class, para);
                //session.delete(e);

//                session.delete("from ContenirExercice ce where ce.id.idExercice="+para);
//                session.delete("from Exercice e where e.idExercice="+para);
                 SQLQuery query = session.createSQLQuery("delete from ContenirExercice where IdExercice ="+para);
                 SQLQuery query2 =  session.createSQLQuery("delete from AvoirCategorieExercice where IdExercice="+para);
                 SQLQuery query3= session.createSQLQuery("delete from AvoirObjectifExercice where IdExercice="+para);
                 SQLQuery query1 = session.createSQLQuery("delete from Exercice where IdExercice ="+para);
                //int result1 = query1.executeUpdate();
//                //SQLQuery query = session.createSQLQuery("delete metier.Exercice where idExercice ="+para);
//                SQLQuery query = session.createSQLQuery("delete Exercice where IdExercice ="+para);
//
//session.delete("ContenirExercice ce where ce.id.idExercice="+para);
//String hqlDelete = "delete Customer where name = :oldName";
//int deletedEntities = s.createQuery( hqlDelete )
//                               .setString( "oldName", oldName )
//                               .executeUpdate();
                
                
                int result2= query2.executeUpdate();
                int result3= query3.executeUpdate();
                int result = query.executeUpdate(); 
                int result1= query1.executeUpdate();
                
                t.commit();
                //session.close();
            } catch (Exception ex) {

                out.println("bug" + ex.getMessage());
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
