package jv.jpa.saexample.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class DbUtil {

    public static User getUserByNameAndPassword(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria c = session.createCriteria(User.class);
        c.add(Restrictions.eq("name", name));
        c.add(Restrictions.eq("password", password));
        User u = (User) c.uniqueResult();
        return u;
    }
}
