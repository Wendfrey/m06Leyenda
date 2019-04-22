package leyenda.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import leyenda.model.Caballero;
import leyenda.model.Princesadragon;
import leyenda.util.HibernateUtil;
import leyenda.view.Consola;

public class PrincesaDragonDao {
	public Princesadragon buscarNombrePrincesa(String nombrePrincesa) {
		Princesadragon princesa = null;
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Query q = s.createQuery("from Princesadragon c where :nombrePrincesa = c.nombrePrincesa");
			q.setString("nombrePrincesa", nombrePrincesa);
			princesa = (Princesadragon) q.list().get(0);
			if(princesa.getRosaconjuros() != null)
				princesa.getRosaconjuros().getCantidadConjuros();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return princesa;
	}
	
	public Princesadragon buscarNombreDragon(String nombreDragon) {
		Princesadragon dragon = null;
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Query q = s.createQuery("from Princesadragon c where :nombreDragon = c.nombreDragon");
			q.setString("nombreDragon", nombreDragon);
			dragon = (Princesadragon) q.list().get(0);
			for(Caballero c : dragon.getCaballeros()) {
				c.getDefensaCaballero();
			}
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dragon;
	}
	
	public void UpdatePrincesadragon(Princesadragon princesadragon) {
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.update(princesadragon);
			s.getTransaction().commit();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(String nombrePrincesa) {
		boolean resultado = false;
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Query q = s.createQuery("from Princesadragon c where :nombrePrincesa = c.princesa");
			q.setString("nombrePrincesa", nombrePrincesa);
			resultado = !q.list().isEmpty();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean existsDragon(String nombreDragon) {
		boolean resultado = false;
		try {
			Session s = HibernateUtil.getSessionFactory().openSession();
			Query q = s.createQuery("from Princesadragon c where :nombreDragon = c.nombreDragon");
			q.setString("nombreDragon", nombreDragon);
			resultado = !q.list().isEmpty();
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
