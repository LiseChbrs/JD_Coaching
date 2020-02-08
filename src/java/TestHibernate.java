
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alied
 */
public class TestHibernate {
    
    public static void main (String[] args)
{
/*----- Ouverture de la session et de la transaction -----*/
Session session = HibernateUtil.getSessionFactory().getCurrentSession();
Transaction t = session.beginTransaction();

// Votre code sera ici !
System.out.println("lol");
t.commit();
System.exit(0);
}

    
}
