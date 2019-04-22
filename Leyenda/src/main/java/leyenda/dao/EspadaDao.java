package leyenda.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Espada;
import leyenda.model.Tiene;
import leyenda.util.HibernateUtil;

public class EspadaDao {
	
	public Espada selectEspada(String nombreEspada){
		Espada espada = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Espada c where :nombreEspada = c.nombreEspada");
			q.setString("nombreEspada", nombreEspada);
			espada = (Espada) q.list().get(0);
			for(Tiene t : espada.getTienes()) {
				if(t.getCaballero() != null) {
					t.getCaballero().getDefensaCaballero();
				}
			}
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return espada;
	}
	
	public boolean exists(String nombreEspada) {
		boolean resultado = false;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Espada c where :nombreEspada = c.nombreEspada");
			q.setString("nombreEspada", nombreEspada);
			resultado = !(q.list().isEmpty());
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void deleteEspada(Espada espada) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(espada);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
