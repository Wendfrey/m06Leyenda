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
			Query q = session.createQuery("from Rosaconjuros c where :0 = c.nombreRosa");
			q.setString(0, nombreRosa);
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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
