package Antonio.Leyenda;

import leyenda.dao.RosaDao;
import leyenda.model.Rosaconjuros;
import leyenda.view.Consola;

public class MenuPrincipal extends Menu {

	@Override
	public String mostrarOpciones() {
		String cadena;
		cadena = "MENU PRINCIPAL LEYENDA\n";
		cadena += "_____________________________\n";
		cadena += "Añadir nueva Rosa de Conjuros 	-> 1\n";
		cadena += "Elimina Espada 					-> 2\n";
		cadena += "Modificar Caballeros de Princesa	-> 3\n";
		cadena += "Mostrar Espada de Caballero\n";
		return cadena;
	}

	@Override
	public int ejecutarOpcion(int opcion) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void AnadirRosa() {
		RosaDao rDao= new RosaDao();
		Consola c = Consola.getSingletonInstance();
		String nombreRosa = null;
		int numConjuros;
		boolean bucle = true;
		do{
			nombreRosa = c.leerString("Escribe el nombre de la nueva Rosa de Conjuros (vacio para salir): ").trim();
			if(nombreRosa.length() > 25) {
				c.escribirSL("El nombre " + nombreRosa + " es demasiado largo.");
			} else if(rDao.selectRosa(nombreRosa) != null){
				c.escribirSL("Ya existe una rosa con el nombre " + nombreRosa + ".");
			} else {
				bucle = false;
			}
		} while(bucle);
		
		if(nombreRosa.length() > 0) {
			Rosaconjuros rosa = new Rosaconjuros(nombreRosa);
			bucle = true;
			
			do {
			}while (bucle);
		}
	}

}
