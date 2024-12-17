package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private String queryHql;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        queryHql = "CREATE TABLE IF NOT EXISTS USER_TABLE" +
                " (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                " name VARCHAR(40)," +
                " lastName VARCHAR(40)," +
                " age TINYINT UNSIGNED)";
        session.createNativeQuery(queryHql).executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        queryHql = "DROP TABLE IF EXISTS USER_TABLE";
        session.createNativeQuery(queryHql).executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from User where id = :id");
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users = session.createQuery("From User", User.class).getResultList();
        tx1.commit();
        session.close();
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        queryHql = "TRUNCATE TABLE USER_TABLE";
        session.createNativeQuery(queryHql).executeUpdate();
        tx1.commit();
        session.close();
    }
}
