import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jm.task.core.jdbc.model.User;

public class HibTest {
    private EntityManager em;

    @Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserTable");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Test
    public void shouldPersistentCategory() {
        User user = new User("Nikolai", "Stolypin", (byte) 35);
        em.persist(user);
        User result = em.find(User.class, 1L);
        assertNotNull(result);
    }

    @After
    public void close() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getEntityManagerFactory().close();
        em.close();
    }
}
