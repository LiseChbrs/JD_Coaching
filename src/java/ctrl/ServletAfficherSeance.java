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
import metier.Seance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import orm.HibernateUtil;

/**
 *
 * @author Administrateur
 */
public class ServletAfficherSeance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)//recherche des elements de la seance
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
                List<Object[]> seance = session.createQuery("select s.idSeance, s.nomSeance, s.typeSeance, d.nomDifficulte from Programme p, Seance s, metier.ContenirSeance cs, Difficulte d   where p.idProgramme=cs.programme.idProgramme and cs.seance.idSeance=s.idSeance and d.idDifficulte = s.difficulte.idDifficulte and p.idProgramme =" + para + " order by cs.id.numOrdre asc").list();
                out.println("<elements>");

                for (int i = 0; i < seance.size(); i++) {//affichage element seance

                    out.println("<element>");
                    out.println("<id>" + seance.get(i)[0] + "</id>");
                    out.println("<nom>" + seance.get(i)[1] + "</nom>");
                    out.println("<type>" + seance.get(i)[2] + "</type>");
                    out.println("<diff>" + seance.get(i)[3] + "</diff>");
                    out.println("</element>");

                }
                out.println("</elements>");
                
                
                session.close();
            } catch (Exception ex) {
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
