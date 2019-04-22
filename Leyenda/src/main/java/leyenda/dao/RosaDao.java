package leyenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Caballero;
import leyenda.model.Rosaconjuros;
import leyenda.util.HibernateUtil;

public class RosaDao {
	
	public Rosaconjuros selectRosa(String nombreRosa){
		Rosaconjuros rosaconjuros = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Rosaconjuros c where :nombreRosa = c.nombreRosa");
			q.setString("nombreRosa", nombreRosa);
			rosaconjuros = (Rosaconjuros) q.list().get(0);
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rosaconjuros;
	}
	
	public void crearRosa(Rosaconjuros r) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(r);
			session.getTransaction().commit();
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(String nombreRosa) {
		boolean resultado = false;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Rosaconjuros c where :nombreRosa = c.nombreRosa");
			q.setString("nombreRosa", nombreRosa);
			resultado = !(q.list().isEmpty());
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
