package com.bank.cyberbank.Repositories;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Entity.User;
import com.bank.cyberbank.Domain.Models.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SessionFactory factory = new Configuration()
            .configure("hibernateconfigUser.cfg.xml")
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    /* CRUD operation */
    public void addUser(User user){
        if(user != null){
            try(Session session = factory.openSession()){
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
            } catch (Exception ex){
                System.out.println("Error with operation");
            }
        } else{
            System.out.println("Model is null");
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
    //todo -------------------------------------
    public void updateUser(UserDTO userDTO){
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, userDTO.getId());

            user.setNameOwnerCard(card_model.getNameOwnerCard());
            user.setExpirationDate(card_model.getExpirationDate());
            user.setLastNameOwnerCard(card_model.getLastNameOwnerCard());

            session.merge(card); // we use marge for update own entity
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error update" + ex);
        }
    }

    public User getUserByLogin(String login) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User where login =:  login");
            query.setParameter("login", login);
            User model = (User) query.uniqueResult();
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
