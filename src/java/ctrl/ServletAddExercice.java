/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CategorieExercice;
import metier.Exercice;
import metier.ObjectifExercice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "ServletAddExercice", urlPatterns = {"/ServletAddExercice"})
public class ServletAddExercice extends HttpServlet {

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

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            /*----- Récupérer les éléments saisies -----*/
            String nom = request.getParameter("nom");
            String desc = request.getParameter("description");
            String image = request.getParameter("lienimg");
            /*----- Ajouter l'exercice dans la BD -----*/
            Exercice e = new Exercice(nom, desc, image);
            session.save(e);

            String[] objs = request.getParameterValues("obj");
            String[] cats = request.getParameterValues("cat");

            for (int i = 0; i < objs.length; i++) {
                ObjectifExercice oe = (ObjectifExercice) session.get(ObjectifExercice.class, Integer.valueOf(objs[i]));
                oe.addExercice(e);
                session.save(e);

            }

            for (int i = 0; i < cats.length; i++) {
                CategorieExercice ce = (CategorieExercice) session.get(CategorieExercice.class, Integer.valueOf(cats[i]));
                ce.addExercice(e);
                session.save(e);
            }

            /*----- Enregistrer dans BD -----*/

            /*----- Commit -----*/
            t.commit();

            RequestDispatcher rd1 = request.getRequestDispatcher("ServletCreationExercice");
            request.setAttribute("msg", "Exercice Ajouté!");
                
            rd1.forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erreur -" + ex.getMessage());
            RequestDispatcher rd1 = request.getRequestDispatcher("erreur");
            request.setAttribute("msg", ex.getMessage());
            rd1.forward(request, response);
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
