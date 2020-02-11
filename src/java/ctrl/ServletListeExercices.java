/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Exercice;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
                System.out.println(caractere);
                int car = Integer.valueOf(caractere);
                
                try {
//                    Query q = session.createQuery("from Exercice ex "+
//                                                                    "where ex.categorieExercices.contains("+ car+")");
//                   List<Exercice> exercices = (List<Exercice>)q.list();
//                    List<Exercice> exercices = (List<Exercice>)session.createQuery("select new metier.Exercice(ex.idExercice, ex.nomExercice, ex.imageExercice) "+
//                                                                    "from Exercice ex "+
//                                                                    "where ex.categorieExercices.idCategorieExercice = "+ car).list();     
                    

                    SQLQuery q = session.createSQLQuery("Select ex.IdExercice, ex.NomExercice, ex.ImageExercice from Exercice ex, AvoirCategorieExercice av where ex.IdExercice = av.IdExercice and av.IdCategorieExercice ="+car);
                    List l = q.list();
                    
                    Iterator it= l.iterator();
                    ArrayList<Exercice> list2=new ArrayList<Exercice>();
                    while(it.hasNext()){
			Object[] objet= (Object[]) it.next();
			list2.add(new  metier.Exercice((Integer)objet[0],(String)objet[1],(String)objet[2]));
 
		}
                                            out.println("<listelement>");

                    for(Exercice ex : list2){
                        out.println("<element>");
                            out.println("<id>"+ex.getIdExercice()+"</id>");
                            out.println("<name>"+ex.getNomExercice()+"</name>");
                            out.println("<image>"+ex.getImageExercice()+"</image>");
                        out.println("</element>");
                    }                         out.println("</listelement>");

                }catch(Exception ex) {
                    out.println(ex.getMessage());
                }
        }
    }

            private static void affichage (List l)
		{
		Iterator e = l.iterator();
		while (e.hasNext())
			{
			Object[] tab_obj = ((Object[]) e.next());

			for (Object obj : tab_obj)
				System.out.print(obj + " ");

			System.out.println("");
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
