package com.bank.cyberbank.Repositories;

import com.bank.cyberbank.Domain.Entity.BankCard;
import com.bank.cyberbank.Domain.Models.BankCardDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankCardRepository {
    private final SessionFactory factory = new Configuration()
            .configure("hibernateconfigRDS.cfg.xml")
            .addAnnotatedClass(BankCard.class)
            .buildSessionFactory();


    //CRUD
    public void Add(BankCard card_model) {
        if (card_model != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.persist(card_model);
                session.getTransaction().commit();
            } catch (Exception ex) {
                System.out.println("Add method error" + ex);
            }
        }

    }

    public List<BankCard> AllCards() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            var cards = session.createQuery("from BankCard ", BankCard.class).getResultList();
            session.getTransaction().commit();
            return cards;
        } catch (Exception ex) {
            System.out.println("AllCards method error" + ex);
            return null;
        }

    }

    public void Update(BankCardDTO card_model) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            var card = session.get(BankCard.class, card_model.getId());

            card.setNameOwnerCard(card_model.getNameOwnerCard());
            card.setExpirationDate(card_model.getExpirationDate());
            card.setLastNameOwnerCard(card_model.getLastNameOwnerCard());

            session.merge(card); // we use marge for update own entity
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error update" + ex);
        }
    }

    public void Delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            var card_model = session.get(BankCard.class, id);
            session.remove(card_model);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            System.out.println("Delete error" + ex);
        }
    }

    //Other method
    public BankCard GetBankCardByNumber(String numbercard) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from BankCard where NumberCard =:  NumberCard");
            query.setParameter("NumberCard", numbercard);
            BankCard model = (BankCard) query.uniqueResult();
            session.getTransaction().commit();
            return model;
        }catch (Exception ex) {
            System.out.println("Error method GetBankCardByNumber" + ex);
            return null;
        }
    }
    public void saveEntity(BankCard bankCard) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(bankCard); // we use marge for update own entity
            session.getTransaction().commit();
        }catch (Exception ex) {
            System.out.println("Error with save" + ex);
        }
    }
}
