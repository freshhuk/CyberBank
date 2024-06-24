package com.bank.cyberbank.Repositories;

import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Models.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final SessionFactory factory = new Configuration()
            .configure("hibernateconfigDebug.cfg.xml")//For prod hibernateconfigRDS for debug hibernateconfigDebug
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
    public User getUser(int id){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            var userModel = session.get(User.class, id);
            session.getTransaction().commit();
            return userModel;
        } catch (Exception ex) {
            System.out.println("Error with operation");
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
            Query query = session.createQuery("from User where login =:  login");
            query.setParameter("login", login);
            Optional<User> model = (Optional<User>) query.uniqueResult();
            session.getTransaction().commit();
            return model;
        }catch (Exception ex) {
            System.out.println("Error method GetBankCardByNumber" + ex);
            return null;
        }
    }
    public void saveEntity(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(user); // we use marge for update own entity
            session.getTransaction().commit();
        }catch (Exception ex) {
            System.out.println("Error with save" + ex);
        }
    }
}
