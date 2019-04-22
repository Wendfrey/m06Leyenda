package leyenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Caballero;
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
}
