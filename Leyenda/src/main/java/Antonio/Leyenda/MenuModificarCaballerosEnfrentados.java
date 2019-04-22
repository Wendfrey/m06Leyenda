package Antonio.Leyenda;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		return cadena;
	}

	@Override
	public int ejecutarOpcion(int opcion) {
		int result = 0;
		switch(opcion) {
		case 1:
			result = opcion;
			AnadirCaballero();
			break;
		case 2:
			result = opcion;
			eliminarCaballeroEnfrentado();
			break;
		case 0:
			result = opcion;
			Consola.getSingletonInstance().escribirSL("Volviendo al menu principal...");
		}
		return result;
	}
	
	public void AnadirCaballero() {
		Consola c = Consola.getSingletonInstance();
		CaballeroDao cDao = new CaballeroDao();
		PrincesaDragonDao pdDao = new PrincesaDragonDao();
		Caballero caballero;
		c.escribirSL("Estos son los caballeros disponibles:");
		List<Caballero> list = cDao.noSiguePrincesaCaballero(dragon.getNombrePrincesa());
		for(int i = 0; i < list.size(); i++) {
			c.escribirSL(">>>>" + i + " ->Caballero " + list.get(i).getNombreCaballero());
		}
		
		int index = 0;
		boolean salir = false, bucle = true;
		String temp;
		do {
			temp = c.leerString("Escribe el numero del caballero que quieres agregar (vacio para cancelar): ").trim();
			if(temp.length() > 0) {
				try {
					index = Integer.parseInt(temp);
					if(index >= 0 && index < list.size()) {
						bucle = false;
					} else {
						c.escribirSL("Escribe un numero dentro del limite (0, "+(list.size()-1) +")");
					}
				} catch(IllegalArgumentException e) {
					c.escribirSL("Escribe un numero valido por favor.");
				}
			}
			else {
				salir = true;
			}
		}while(bucle && !salir);
		
		if(!salir) {
			caballero = list.get(index);
			if(caballero.getPrincesadragon() != null) {
				temp = c.leerString("El caballero tiene asignado ya a un dragon, quieres sobreescribirlo? (S para sobreescribir):").trim().toUpperCase();
				if(temp.equals("S")) {
					caballero.setPrincesadragon(dragon);
					dragon.getCaballeros().add(caballero);
					cDao.updateCaballero(caballero);
					c.escribirSL("¡Se ha añadido al caballero " + caballero.getNombreCaballero() + " a enfrentarse a " + dragon.getNombreDragon() + "!");
				} else {
					c.escribirSL("Operacion cancelada. Volviendo al menu...");
				}
			}
		}
		else {
			c.escribirSL("Operacion cancelada. Volviendo al menu...");
		}
	}
	
	public void eliminarCaballeroEnfrentado() {
		Consola c = Consola.getSingletonInstance();
		CaballeroDao cDao = new CaballeroDao();
		PrincesaDragonDao pdDao = new PrincesaDragonDao();
		Caballero caballero;
		c.escribirSL("Estos son los caballeros disponibles para eliminar:");
		Caballero[] list = new Caballero[dragon.getCaballeros().size()];
		Iterator<Caballero> iterator = dragon.getCaballeros().iterator();
		for(int i = 0; i < list.length; i++) {
			list[i] = iterator.next();
			c.escribirSL(">>>>" + i + " ->Caballero " + list[i].getNombreCaballero());
		}
		
		int index = 0;
		boolean salir = false, bucle = true;
		String temp;
		do {
			temp = c.leerString("Escribe el numero del caballero que quieres eliminar (vacio para cancelar): ").trim();
			if(temp.length() > 0) {
				try {
					index = Integer.parseInt(temp);
					if(index >= 0 && index < list.length) {
						bucle = false;
					} else {
						c.escribirSL("Escribe un numero dentro del limite (0, "+(list.length-1) +")");
					}
				} catch(IllegalArgumentException e) {
					c.escribirSL("Escribe un numero valido por favor.");
				}
			}
			else {
				salir = true;
			}
		}while(bucle && !salir);
		
		if(!salir) {
			caballero = list[index];
			temp = c.leerString("¿Estas seguro de eliminar a "+ caballero.getNombreCaballero() + "? (S para eliminar)").trim().toUpperCase();
			if(temp.equals("S")) {
				dragon.getCaballeros().remove(caballero);
				caballero.setPrincesadragon(null);
				cDao.updateCaballero(caballero);
				c.escribirSL("¡Se ha eliminado al caballero " + caballero.getNombreCaballero() + " a enfrentarse a " + dragon.getNombreDragon() + "!");
				
			}
			else {
				c.escribirSL("Operacion cancelada. Volviendo al menu...");
			}
		}
		else {
			c.escribirSL("Operacion cancelada. Volviendo al menu...");
		}
	}
}
