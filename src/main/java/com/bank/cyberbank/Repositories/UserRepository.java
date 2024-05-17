package com.bank.cyberbank.Repositories;

import com.bank.cyberbank.Domain.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
            }
            catch (Exception ex){
                System.out.println("Error with operation");
            }
        }
        else{
            System.out.println("Model is null");
        }
    }
    public User getUser(int id){

        return null;
    }
    public void deleteUser(User user){

    }
    public void updateUser(User user){

    }
}
