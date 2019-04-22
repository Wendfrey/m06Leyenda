package leyenda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Caballero;
import leyenda.model.Espada;
import leyenda.model.Tiene;
import leyenda.util.HibernateUtil;

public class TienesDao {
	
	public List<Tiene> selectEspadasCaballero(String nombreCaballero){
		List<Tiene> resultado = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery("from Tiene c where :nombreCaballero = c.id.nombreCaballero");
			q.setString("nombreCaballero", nombreCaballero);
			resultado = q.list();
			for(Tiene t: resultado) {
				t.getEspada().getAtaqueEspada();
			}
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
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
