package ru.rb.rest;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ru.rb.data.Сurrency;

import static org.junit.Assert.*;

public class Main {
    
  private static EntityManagerFactory emf; // = Persistence.createEntityManagerFactory("app-main");
  private static EntityManager em;
  //private static EntityTransaction tx;
    
  public static void main(String ... args) {
    emf = Persistence.createEntityManagerFactory("app-main");
    em = emf.createEntityManager();
    //tx = em.getTransaction();
    try {
    //tx.begin();
        Query qu = em.createQuery("select CUR from Сurrency CUR where CUR.nm = :name");
        qu.setParameter("name", "USD");
        Сurrency cur = (Сurrency)qu.getSingleResult();
        assertEquals("840", cur.getCd());
        assertEquals(LocalDate.of(9999, 12, 31), cur.getLDt());
    } finally {
      //tx.commit();
      close();
    }
  }
  
  public static void close()
  {
    if ( em != null && em.isOpen() ) em.close();
    if ( emf != null && emf.isOpen() ) emf.close();
  }
}