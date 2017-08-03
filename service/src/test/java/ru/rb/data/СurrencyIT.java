package ru.rb.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import javax.persistence.EntityTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class СurrencyIT {
  private static EntityManagerFactory emf;
  private static EntityManager em;
  
  @BeforeClass
  public static void initEntityManager() {
      emf = Persistence.createEntityManagerFactory("app-main");
      em = emf.createEntityManager();
  }

  @AfterClass
  public static void closeEntityManager() {
    if ( em != null && em.isOpen() ) em.close();
    if ( emf != null && emf.isOpen() ) emf.close();
  }
  
  @Test
  public void should1GetCurrencyUsd() {
    Query qu = em.createQuery("select CUR from Сurrency CUR where CUR.nm = :name");
    qu.setParameter("name", "USD");
    Сurrency cur = (Сurrency)qu.getSingleResult();
    assertEquals("840", cur.getCd());
    assertEquals(LocalDate.of(9999, 12, 31), cur.getLDt());
  }
  
  @Test
  public void should2InsertCurrencyEth() {
    Сurrency cur = new Сurrency( "777", "ETH", "Ethereum", "Ethereum", LocalDate.of(2001,01,01), LocalDate.of(9999,12,31), (byte)0, (byte)0, "777" );
    EntityTransaction tx = em.getTransaction();
    try {
        tx.begin();
        em.persist(cur);
        tx.commit();
        assertNotNull("ID should not be null", cur.getCtId());
    } catch (Exception ex) {
        if ( tx.isActive() ) tx.rollback();
        throw ex;
    }
  }
  
  @Test
  public void should3UpdateCurrencyEth() {
    Query qu = em.createQuery("select CUR from Сurrency CUR where CUR.nm = :name");
    qu.setParameter("name", "ETH");
    Сurrency cur = (Сurrency)qu.getSingleResult();
    assertEquals("777", cur.getSpclCd());
    cur.setSpclCd("146");
    EntityTransaction tx = em.getTransaction();
    try {
        tx.begin();
        em.merge(cur);
        tx.commit();
    } catch (Exception ex) {
        if ( tx.isActive() ) tx.rollback();
        throw ex;
    }
    cur = (Сurrency)qu.getSingleResult();    
    assertEquals("146", cur.getSpclCd());
  }
  
  @Test
  public void should4DeleteCurrencyEth() {
    Query qu = em.createQuery("select CUR from Сurrency CUR where CUR.nm = :name");
    qu.setParameter("name", "ETH");
    Сurrency cur = (Сurrency)qu.getSingleResult();      
    EntityTransaction tx = em.getTransaction();
    try {
        tx.begin();
        em.remove(cur);
        tx.commit();
    } catch (Exception ex) {
        if ( tx.isActive() ) tx.rollback();
        throw ex;
    }
    qu = em.createQuery("select count(*) from Сurrency CUR where CUR.nm = :name");
    qu.setParameter("name", "ETH");
    long val = (Long)qu.getSingleResult();
    //long val = (Long)res[0];
    assertEquals(0L, val);
  }
}
