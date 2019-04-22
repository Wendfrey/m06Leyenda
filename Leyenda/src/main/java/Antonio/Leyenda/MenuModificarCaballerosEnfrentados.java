package Antonio.Leyenda;

import java.util.List;

import leyenda.dao.CaballeroDao;
import leyenda.dao.PrincesaDragonDao;
import leyenda.model.Caballero;
import leyenda.model.Princesadragon;
import leyenda.view.Consola;

public class MenuModificarCaballerosEnfrentados extends Menu {

	private Princesadragon dragon; 
	
	public MenuModificarCaballerosEnfrentados(Princesadragon dragon) {
		this.dragon = dragon;
	}
	@Override
	public String mostrarOpciones() {
		String cadena;
		cadena = "Modificar enfrentamientos del dragon " + dragon.getNombreDragon() + "\n";
		cadena += "_______________________________\n";
		cadena += "Salir -> 0\n";
		cadena += "Añadir caballero enfrentado -> 1\n";
		cadena += "Quitar caballero enfrentado -> 2\n";
		cadena += "Escoge una opcion:\n";
		return null;
	}

	@Override
	public int ejecutarOpcion(int opcion) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void AnadirCaballero() {
		Consola c = Consola.getSingletonInstance();
		CaballeroDao cDao = new CaballeroDao();
		PrincesaDragonDao pdDao = new PrincesaDragonDao();
		Caballero caballero;
		c.escribirSL("Estos son los caballeros disponibles:");
		List<Caballero> list = cDao.noSiguePrincesaCaballero(dragon.getNombrePrincesa());
		for(int i = 0; i < list.size(); i++) {
			c.escribirSL("Caballero " + list.get(i).getNombreCaballero());
		}
	}
}
