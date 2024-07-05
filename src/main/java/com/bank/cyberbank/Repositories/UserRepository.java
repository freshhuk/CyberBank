package com.bank.cyberbank.Repositories;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Models.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final SessionFactory factory = new Configuration()
            .configure("hibernateconfigRDS.cfg.xml")//For prod hibernateconfigRDS for debug hibernateconfigDebug
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
    private static final String STATUSCODE200_MESSAGE = "Successful";


    /* CRUD operation */
    public String addUser(User user){
        if(user != null){
            try(Session session = factory.openSession()){
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
                return STATUSCODE200_MESSAGE;
            } catch (Exception ex){
                System.out.println("Error with operation" + ex);
                return "Error";
            }
        } else{
            System.out.println("Model is null");
            return "Model is null";
        }
    }

    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            var users = session.createQuery("from User ", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } catch (Exception ex) {
            System.out.println("Allusers method error" + ex);
            return null;
        }

    }
    public void deleteUser(int id){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            var userModel = session.get(User.class, id);
            session.remove(userModel);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Delete error" + ex);
        }
    }

    public void updateUser(UserDTO userDTO){
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, userDTO.getId());

            user.setLogin(userDTO.getNew_login());
            user.setPassword(userDTO.getNew_password());

            session.merge(user); // we use marge for update own entity
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error update" + ex);
        }
    }

    public Optional<User> getUserByLogin(String login) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User where login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (Exception ex) {
            System.out.println("Error method getUserByLogin" + ex);
            return Optional.empty();
        }
    }
}
