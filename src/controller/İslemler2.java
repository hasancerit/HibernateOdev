/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.swing.JOptionPane;
import model.Ogretimuyesi;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class İslemler2 {
    SessionFactory sessionFactory;
    Session session;
    int sayac=-1;
    List<Ogretimuyesi>result;
    
    public İslemler2(){
        sessionFactory = new Configuration().configure().buildSessionFactory(); 
        listeYenile();    
    }
    
    public void listeYenile() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        

        result = (List) session.createQuery("from Ogretimuyesi").list();
        
        session.close();
    }
    
    public Ogretimuyesi ileri(){
        Ogretimuyesi ogr = null;
        sayac++;
        
        try {
            ogr =  result.get(sayac);  
        } catch (ArrayIndexOutOfBoundsException e) {
              JOptionPane.showMessageDialog(null , "Liste Bitti!");
              sayac--;
              ogr =  result.get(sayac); 
        }
        
        return ogr;
        
    }
    
    public Ogretimuyesi geri(){
        sayac--;
        Ogretimuyesi ogr = null;
        
         try {
             ogr =  result.get(sayac);
        } catch (ArrayIndexOutOfBoundsException e) {
              JOptionPane.showMessageDialog(null , "Liste Bitti!");
              sayac++;
              ogr =  result.get(sayac); 
        }
        return ogr;
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
        listeYenile();
        
    }
}
