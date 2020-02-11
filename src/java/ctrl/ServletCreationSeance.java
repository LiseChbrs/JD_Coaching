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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CategorieExercice;
import metier.CategorieSeance;
import metier.Difficulte;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author alied
 */
@WebServlet(name = "ServletCreationSeance", urlPatterns = {"/ServletCreationSeance"})
public class ServletCreationSeance extends HttpServlet {

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

        String action = request.getParameter("action");
        switch (action) {
            case "versForm":
                try {
                    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction t = session.beginTransaction();
                    List<Difficulte> queryDifficulte = (List<Difficulte>) session.createQuery(
                            "select new metier.Difficulte(d.idDifficulte"
                            + " ,d.nomDifficulte)"
                            + " from Difficulte d").list();
                    List<CategorieSeance> queryCategorieSeance = (List<CategorieSeance>) session.createQuery(
                            "select new metier.CategorieSeance(cs.idCategorieSeance"
                            + " ,cs.nomCategorieSeance)"
                            + " from CategorieSeance cs").list();
                    List<CategorieExercice> queryCategorieExercice = (List<CategorieExercice>) session.createQuery(
                            "select new metier.CategorieExercice(ce.idCategorieExercice"
                            + " ,ce.nomCategorieExercice)"
                            + " from CategorieExercice ce").list();
                    RequestDispatcher rd = request.getRequestDispatcher("creationSeance");
                    request.setAttribute("listeCategorie", queryCategorieSeance);
                    request.setAttribute("listeDifficulte", queryDifficulte);
                    request.setAttribute("listeCategorieExercice", queryCategorieExercice);
                    session.close();
                    rd.forward(request, response);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

        }

        //String nomSeance = request.getParameter("nomSeance");
        //String typeSeance = request.getParameter("typeSeance");
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
