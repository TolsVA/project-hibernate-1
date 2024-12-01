package com.game;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Создаём SessionFactory из конфигурации hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Player.class)  // Добавляем все сущности
                    .addAnnotatedClass(Race.class)
                    .addAnnotatedClass(Profession.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed!" + e);
        }
    }

    // Получаем текущую сессию
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Закрываем SessionFactory (при завершении работы приложения)
    public static void shutdown() {
        sessionFactory.close();
    }
}

