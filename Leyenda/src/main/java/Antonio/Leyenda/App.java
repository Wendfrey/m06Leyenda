package Antonio.Leyenda;

import leyenda.dao.CaballeroDao;
import leyenda.model.Caballero;
import leyenda.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HibernateUtil.getSessionFactory();
        /*CaballeroDao cDao = new CaballeroDao();
        for(Caballero c : cDao.selectCaballero()) {
        	System.out.println(c.getNombreCaballero() + ", " + c.getVidaCaballero() + ", " + c.getDefensaCaballero() + ", " + c.getPrincesadragon().getNombrePrincesa());
        }*/
        Menu m = new MenuPrincipal();
        m.bucle();
    }
}
