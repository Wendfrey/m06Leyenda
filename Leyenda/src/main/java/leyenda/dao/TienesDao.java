package leyenda.dao;

import org.hibernate.Session;

import leyenda.model.Espada;
import leyenda.model.Tiene;
import leyenda.util.HibernateUtil;

public class TienesDao {
	public void deleteTiene(Tiene tiene) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(tiene);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
