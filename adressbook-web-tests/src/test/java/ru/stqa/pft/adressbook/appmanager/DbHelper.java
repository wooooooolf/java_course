package ru.stqa.pft.adressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData selectUserById(int userId) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    UserData user = (UserData) session.createQuery( "from UserData where id="+userId ).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return user;
  }
}


