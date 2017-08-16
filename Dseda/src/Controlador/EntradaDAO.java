/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Entrada;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Bian
 */
public class EntradaDAO {
    
    public void alta(Entrada en)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(en);
            tx.commit();
        }
        catch(Exception e)
        {
            if (tx.isActive())
		tx.rollback();
                    e.printStackTrace();
		throw e;
        }
        session.close();
        JOptionPane.showMessageDialog(null, "Entrada agregada");
    }
    
    public void modificar(Entrada en, int id)
    {
        Entrada entrada = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        entrada = (Entrada)session.get(Entrada.class, id);
        entrada.setCantidad(en.getCantidad());
        entrada.setFecha(en.getFecha());
        entrada.setProducto(en.getProducto());
        entrada.setProveedor(en.getProveedor());
        entrada.setEstado(en.isEstado());
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.merge(entrada);
          tx.commit();
        }
        catch(Exception e)
        {
            if (tx.isActive())
		tx.rollback();
                    e.printStackTrace();
		throw e;
        }
            session.close();
            JOptionPane.showMessageDialog(null, "Entrada modificada");
    }
    
   /* public void baja(int id)
    {
        Entrada en = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        en = (Entrada)session.get(Entrada.class, id);
        en.setEstado(false);
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.update(en);
          tx.commit();
        }
        catch(Exception e)
        {
            if (tx.isActive())
		tx.rollback();
                    e.printStackTrace();
		throw e;
        }
            session.close();
            JOptionPane.showMessageDialog(null, "Entrada dada de baja");
    }
    */
    
    public Entrada buscarPorId(int id)
    {
        Entrada en = null;
        try{           
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            en = (Entrada)session.get(Entrada.class,id);
            /*if(p != null)
            {
                JOptionPane.showMessageDialog(null, "Entrada encontrada");
            }*/
            tx.commit();
            session.close();
        } catch(HibernateException e)
        {
            JOptionPane.showMessageDialog(null, "Entrada no encontrado");
        }
        
        return en;
    }
    
    public List<Entrada> listar()
    {
        List<Entrada> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            lista = session.createQuery("FROM Entrada").list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    
    public List<Entrada> buscarPorFecha(Date fecha)
    {
        List<Entrada> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Entrada e WHERE e.fecha LIKE :fecha");
            query.setParameter("fecha", "%"+fecha+"%");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    
   
}
