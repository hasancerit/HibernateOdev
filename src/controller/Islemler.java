/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.mchange.v2.log.MLog.config;
import java.lang.annotation.*;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import model.Ogretimuyesi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)  @interface Bilgi 
{
    String bilgi();
    String tarih();
    String yazar();
}
    
    
/**
 *
 * @author Hasan
 */


public class Islemler {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session;
    public List<Ogretimuyesi> result;
    
    public Islemler(){
        upd();     
    }
    
    public void ekle(String AdSoyad, String Sehir,String SicilNo,String TelNo){
      session=sessionFactory.openSession();
      session.beginTransaction();
      
      Ogretimuyesi alan = new Ogretimuyesi();
      alan.setAdsoyad(AdSoyad);
      alan.setSehir(Sehir);
      alan.setSicilno(SicilNo);
      alan.setTelno(TelNo);
      session.save(alan);
      session.getTransaction().commit();
      session.close();
      
      upd();
    }
     
    public void sil(int id){
      session=sessionFactory.openSession();
      session.beginTransaction();
      
      Ogretimuyesi ou = session.get(Ogretimuyesi.class, id);
      session.delete(ou);
      
      session.getTransaction().commit();
      session.close();
      upd();
    }

    public Islemler(Session session, List<Ogretimuyesi> result) {
        this.session = session;
        this.result = result;
    }

    
    public void guncelle(int id,String isim,String Sicil,String sehir,String Tel){
        session=sessionFactory.openSession();
        session.beginTransaction();
        
        Ogretimuyesi newO = new Ogretimuyesi();
        
        newO.setAdsoyad(isim);
        newO.setOgretimuyesiiid(id);
        newO.setSicilno(Sicil);
        newO.setTelno(Tel);
        newO.setSehir(sehir);
        
        session.update(newO);
        session.getTransaction().commit();
        upd();
    }
    
    public void upd(){
        session=sessionFactory.openSession();
        session.beginTransaction();
      
         result = (List<Ogretimuyesi>) session.createQuery("from Ogretimuyesi").list();
    
         session.close();
    }
}
