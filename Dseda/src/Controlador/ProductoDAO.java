/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.*;
;

/**
 *
 * @author Bian
 */
public class ProductoDAO {
    
    public void alta(Producto p)
    {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(p);
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
        JOptionPane.showMessageDialog(null, "Producto agregado");
    }
    
    public void modificar(Producto p, int id)
    {
        Producto prod = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        prod = (Producto)session.get(Producto.class, id);
        prod.setCodigo(p.getCodigo());
        prod.setDescripcion(p.getDescripcion());
        prod.setImagen(p.getImagen());
        prod.setPrecio(p.getPrecio());
        prod.setProveedor(p.getProveedor());
        prod.setStockMinimo(p.getStockMinimo());
        prod.setEstado(p.isEstado());
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.merge(prod);
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
            JOptionPane.showMessageDialog(null, "Producto modificado");
    }
    
   /* public void baja(int id)
    {
        Producto prod = null;
        
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
            
        prod = (Producto)session.get(Producto.class, id);
        prod.setEstado(false);
            
        Transaction tx = session.beginTransaction();
        try
        {
          session.update(prod);
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
            JOptionPane.showMessageDialog(null, "Producto dado de baja");
    }
    */
    
    public Producto buscarPorId(int id)
    {
        Producto p = null;
        try{           
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            p = (Producto)session.get(Producto.class,id);
            /*if(p != null)
            {
                JOptionPane.showMessageDialog(null, "Producto encontrado");
            }*/
            tx.commit();
            session.close();
        } catch(HibernateException e)
        {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
        
        return p;
    }
    
    public List<Producto> listar()
    {
        List<Producto> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            lista = session.createQuery("FROM Producto").list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error. Listar productos");
        }
        return lista;
    }
    
    public List<Producto> buscarPorCodigo(String codigo)
    {
        List<Producto> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Producto p WHERE p.codigo LIKE :codigo");
            query.setParameter("codigo", codigo+"%");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error. Producto por codigo");
        }
        return lista;
    }
    
    public List<Producto> alarma()
    {
        List<Producto> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Producto p WHERE p.stock <= p.stockMinimo");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error. Lista stock minimo");
        }
        return lista;
    }
    
   
    public List<Producto> buscarPorCodigoNombre(String cadena)
    {
        List<Producto> lista = null;
        try
        {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("FROM Producto p WHERE p.codigo LIKE :cadena OR p.descripcion LIKE :cadena");
            query.setParameter("cadena", "%"+cadena+"%");
            lista = query.list();
            tx.commit();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error. Producto por codigo o descripcion");
        }
        return lista;
    }
    
}
