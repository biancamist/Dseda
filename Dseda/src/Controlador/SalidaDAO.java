/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Salida;
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
public class SalidaDAO {
    
    public void alta(Salida sa)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(sa);
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
        JOptionPane.showMessageDialog(null, "Salida agregada");
    }
    
    public void modificar(Salida sa, int id)
    {
        Salida salida = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        salida = (Salida)session.get(Salida.class, id);
        salida.setCantidad(sa.getCantidad());
        salida.setFecha(sa.getFecha());
        salida.setProducto(sa.getProducto());
        salida.setCliente(sa.getCliente());
        salida.setEstado(sa.isEstado());
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.merge(salida);
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
            JOptionPane.showMessageDialog(null, "Salida modificada");
    }
    
   /* public void baja(int id)
    {
        Salida sa = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        sa = (Salida)session.get(Salida.class, id);
        sa.setEstado(false);
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.update(sa);
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
            JOptionPane.showMessageDialog(null, "Salida dada de baja");
    }
    */
    
    public Salida buscarPorId(int id)
    {
        Salida sa = null;
        try{           
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            sa = (Salida)session.get(Salida.class,id);
            /*if(p != null)
            {
                JOptionPane.showMessageDialog(null, "Salida encontrada");
            }*/
            tx.commit();
            session.close();
        } catch(HibernateException e)
        {
            JOptionPane.showMessageDialog(null, "Salida no encontrado");
        }
        
        return sa;
    }
    
    public List<Salida> listar()
    {
        List<Salida> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            lista = session.createQuery("FROM Salida").list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    
    public List<Salida> buscarPorFecha(Date fecha)
    {
        List<Salida> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Salida s WHERE s.fecha LIKE :fecha");
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
