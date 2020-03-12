package yaroslav.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yaroslav.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> getAllUsers() {
        return getSession().createQuery("FROM User").list();
    }

    @Override
    public void addUser(User user) {
        getSession().persist(user);
    }

    @Override
    public void updateUser(User user) {
        getSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        getSession().delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public User getUser(String name, String pass) {
        Transaction transaction = null;
        User user = null;
        try {
            transaction = getSession().beginTransaction();

            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(
                    builder.equal(root.get("name"), name),
                    builder.equal(root.get("password"), pass)
            );
            user = getSession().createQuery(criteria).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        return getSession().get(User.class, name);
    }
}
