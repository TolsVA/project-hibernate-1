package com.game.repository;

import com.game.HibernateUtil;
import com.game.entity.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {
//    private final SessionFactory sessionFactory;

    public PlayerRepositoryDB() {
        Session session = HibernateUtil.getSession();
        session.close();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        try (Session session = HibernateUtil.getSession()){
            Query<Player> nativeQuery = session.createNamedQuery("Player_getAll", Player.class);
            nativeQuery.setFirstResult(pageNumber * pageSize);
            nativeQuery.setMaxResults(pageSize);
            return nativeQuery.list();
        }
    }

    @Override
    public int getAllCount() {
        try (Session session = HibernateUtil.getSession()){
            Query<Long> namedQuery = session.createNamedQuery("Player_getAllCount", Long.class);
            return Math.toIntExact(namedQuery.getSingleResult());
        }
    }

    @Override
    public Player save(Player player) {
        try (Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(player);
            transaction.commit();
            return player;
        }
    }

    @Override
    public Player update(Player player) {
        try (Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(player);
            transaction.commit();
            return player;
        }
    }

    @Override
    public Optional<Player> findById(long id) {
        try (Session session = HibernateUtil.getSession()) {
            Player player = session.find(Player.class, id);
            return Optional.of(player);
        }
    }

    @Override
    public void delete(Player player) {
        try (Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(player);
            transaction.commit();
        }
    }

    @PreDestroy
    public void beforeStop() {
        HibernateUtil.shutdown();
    }
}