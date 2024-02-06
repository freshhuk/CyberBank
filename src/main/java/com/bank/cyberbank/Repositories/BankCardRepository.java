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
public class BankCardRepository
{
    private final SessionFactory factory = new Configuration()
            .configure("hibernateconfig.cfg.xml")
            .addAnnotatedClass(BankCard.class)
            .buildSessionFactory();


    //CRUD
    public void Add(BankCard card_model)
    {
        if(card_model != null)
        {
            try(Session session = factory.openSession())
            {
                session.beginTransaction();
                session.persist(card_model);
                session.getTransaction().commit();
            }
        }

    }
    public List<BankCard> AllCards()
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var cards = session.createQuery("from BankCard ", BankCard.class).getResultList();
            session.getTransaction().commit();
            return cards;
        }
    }
    public void Update(BankCardDTO card_model)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var card = session.get(BankCard.class ,card_model.getId());
            card.setNameOwnerCard(card_model.getNameOwnerCard());
            card.setExpirationDate(card_model.getExpirationDate());
            card.setLastNameOwnerCard(card_model.getLastNameOwnerCard());
            session.getTransaction().commit();
        }
    }
    public void Delete(int id)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var card_model = session.get(BankCard.class,id);
            session.remove(card_model);
            session.getTransaction().commit();
        }
    }
    //Other method
    public BankCard GetBankCardByNumber(String numbercard)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            Query query = session.createQuery("from BankCard where NumberCard =:  NumberCard");
            query.setParameter("NumberCard", numbercard);
            BankCard model = (BankCard) query.uniqueResult();
            session.getTransaction().commit();
            return model;
        }
    }
    public BankCard GetBankCardById(int id)
    {
        try(Session session = factory.openSession())
        {
            session.beginTransaction();
            var model = session.get(BankCard.class, id);
            session.getTransaction().commit();
            return model;
        }
    }
}
