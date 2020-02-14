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
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author alied
 */
public class ServletSupExo extends HttpServlet {

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
                List<Object[]> seance = session.createQuery("select  s.idSeance,e.nomExercice, s.nomSeance from Seance s, metier.ContenirExercice ce, Exercice e"
                        + " where e.idExercice=ce.id.idExercice and s.idSeance=ce.id.idSeance "
                        + " and (ce.id.numOrdre!=1 or ce.id.numOrdre != (select max(ce.id.numOrdre)"
                        + " from Seance s1, metier.ContenirExercice ce1, Exercice e1"
                        + " where e1.idExercice=ce1.id.idExercice and s1.idSeance=ce1.id.idSeance and s1.idSeance=s.idSeance and e1.idExercice="+para+")) "
                        + " and e.idExercice =" + para ).list();
                out.println("<elements>");
                List<Object[]> exo = session.createQuery("select e.nomExercice from metier.Exercice e "
                        + " where e.idExercice="+para+" and e.idExercice not in (select ce.id.idExercice from metier.ContenirExercice ce)").list();
                if(exo.size()!=0){
                    out.println("<element>true</element>");
                }
                else{
                                  if(seance.size()<1){
                    out.println("<element>false</element>");                   
                }
                for (int i = 0; i < seance.size(); i++) {//affichage element seance
                    out.println("<element>");
                    out.println("<id>" + seance.get(i)[0] + "</id>");
                    out.println("<nom>" + seance.get(i)[1] + "</nom>");
                    out.println("<nomSeance>" + seance.get(i)[2] + "</nomSeance>");
                    out.println("</element>");
                }
                    
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
