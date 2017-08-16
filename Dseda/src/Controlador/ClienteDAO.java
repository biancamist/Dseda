/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
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
public class ClienteDAO {
    
    public void alta(Cliente c)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(c);
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
        JOptionPane.showMessageDialog(null, "Cliente agregado");
    }
    
    public void modificar(Cliente c, int id)
    {
        Cliente cliente = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        cliente = (Cliente)session.get(Cliente.class, id);
        cliente.setNombreCompleto(c.getNombreCompleto());
        cliente.setCuit(c.getCuit());
        cliente.setDireccion(c.getDireccion());
        cliente.setEstado(c.isEstado());
        cliente.setTelefono(c.getTelefono());
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.merge(cliente);
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
            JOptionPane.showMessageDialog(null, "Cliente modificado");
    }
    
   /* public void baja(int id)
    {
        Cliente cliente = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        cliente = (Cliente)session.get(Cliente.class, id);
        cliente.setEstado(false);
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.update(cliente);
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
            JOptionPane.showMessageDialog(null, "Cliente eliminado");
    }
    */
    
    public Cliente buscarPorId(int id)
    {
        Cliente c = null;
        try{           
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            c = (Cliente)session.get(Cliente.class,id);
            /*if(p != null)
            {
                JOptionPane.showMessageDialog(null, "CLiente encontrado");
            }*/
            tx.commit();
            session.close();
        } catch(HibernateException e)
        {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        }
        
        return c;
    }
    
    public List<Cliente> listar()
    {
        List<Cliente> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            lista = session.createQuery("FROM Cliente").list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    
    public List<Cliente> buscarPorCuit(String cuit)
    {
        List<Cliente> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Cliente c WHERE c.cuit LIKE :cuit");
            query.setParameter("cuit", "%"+cuit+"%");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    
   /*
    public List<Cliente> buscarPorCuitNombre(String cadena)
    {
        List<Cliente> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Cliente c WHERE c.cuit LIKE :cadena OR c.nombre_completo LIKE :cadena");
            query.setParameter("cadena", "%"+cadena+"%");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return lista;
    }
    */
}
