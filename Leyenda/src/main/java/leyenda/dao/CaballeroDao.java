package leyenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Caballero;
import leyenda.model.Tiene;
import leyenda.util.HibernateUtil;

public class CaballeroDao {
	
	public Caballero selectCaballero(String nombreCaballero){
		Caballero caballero = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Caballero c where :nombreCaballero = c.nombreCaballero");
			q.setString("nombreCaballero", nombreCaballero);
			caballero = (Caballero) q.list().get(0);
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return caballero;
	}
	
	public List<Caballero> selectCaballero(){
		List<Caballero> listaC = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Caballero");
			listaC = q.list();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaC;
	}
	
	public List<Caballero> noSiguePrincesaCaballero(String nombrePrincesa){
		List<Caballero> listaC = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Caballero c where not :nombrePrincesa = c.princesadragon");
			q.setString("nombrePrincesa", nombrePrincesa);
			listaC = q.list();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaC;
	}
	
	public boolean exists(String nombreCaballero) {
		boolean resultado = false;
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Query q = s.createQuery("from Caballero c where :nombreCaballero = c.nombreCaballero");
			q.setString("nombreCaballero", nombreCaballero);
			resultado = !q.list().isEmpty();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void updateCaballero(Caballero caballero) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(caballero);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
