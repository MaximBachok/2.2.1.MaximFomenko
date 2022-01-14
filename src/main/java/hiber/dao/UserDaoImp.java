package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        if (user.getCar() != null) {
            session.save(user.getCar());
        }
        session.save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        String HQL = "FROM User WHERE car.model=?1 AND  car.series =?2 ";

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL, User.class);
        query.setParameter(1, model);
        query.setParameter(2, series);
        return query.getResultList();

    }

}
