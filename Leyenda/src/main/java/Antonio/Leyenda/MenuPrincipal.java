package Antonio.Leyenda;

import leyenda.dao.PrincesaDragonDao;
import leyenda.dao.RosaDao;
import leyenda.model.Princesadragon;
import leyenda.model.Rosaconjuros;
import leyenda.view.Consola;

public class MenuPrincipal extends Menu {

	@Override
	public String mostrarOpciones() {
		String cadena;
		cadena = "MENU PRINCIPAL LEYENDA\n";
		cadena += "_____________________________\n";
		cadena += "Añadir nueva Rosa de Conjuros	-> 1\n";
		cadena += "Elimina Espada					-> 2\n";
		cadena += "Modificar Caballeros de Princesa	-> 3\n";
		cadena += "Mostrar Espada de Caballero\n";
		cadena += "Escoge una opcion: "; 
		return cadena;
	}

	@Override
	public int ejecutarOpcion(int opcion) {
		// TODO Auto-generated method stub
		int resultado = 0;
		switch(opcion) {
			case 1:
				resultado = 1;
				AnadirRosa();
				break;
		}
		return resultado;
	}
	
	private void AnadirRosa() {
		RosaDao rDao= new RosaDao();
		Consola c = Consola.getSingletonInstance();
		String nombreRosa = null;
		int numConjuros = 0;
		boolean bucle = true, salir = false;
		
		do{
			nombreRosa = c.leerString("Escribe el nombre de la nueva Rosa de Conjuros (vacio para salir): ").trim();
			if(nombreRosa.length() == 0) {
				salir = true;
			} else if(nombreRosa.length() > 25) {
				c.escribirSL("El nombre " + nombreRosa + " es demasiado largo.");
			} else if(rDao.exists(nombreRosa)){
				c.escribirSL("Ya existe una rosa con el nombre " + nombreRosa + ".");
			} else {
				bucle = false;
			}
		} while(bucle && !salir);
		
		if(!salir) {
			Rosaconjuros rosa = new Rosaconjuros(nombreRosa);
			bucle = true;
			
			do {
				String temp = c.leerString("Inserta el numero de conjuros de la rosa (vacio para salir): ").trim();
				if(temp.length() > 0) {
					try {
						numConjuros = Integer.parseInt(temp);
						bucle = false;
					} catch(IllegalArgumentException e) {
						c.escribirSL("Escribe un numero valido por favor.");
					}
				} else {
					salir = true;
				}
			}while (bucle && !salir);
			
			if(!salir) {
				rosa.setCantidadConjuros(numConjuros);
				rDao.crearRosa(rosa);
				c.escribirSL("Se ha guardado la rosa!");
				recursivoPreguntaAsignarAPrincesaRosa(rosa);
			}
			else {
				c.escribirSL("Volviendo al menu...");
			}
		}
		else {
			c.escribirSL("Volviendo al menu...");
		}
	}
	
	private void recursivoPreguntaAsignarAPrincesaRosa(Rosaconjuros rosa) {
		Consola c = Consola.getSingletonInstance();
		String temp = c.leerString("Quieres asignarle una princesa a la rosa " + rosa.getNombreRosa() + "? (S para asignar)").toUpperCase();
		boolean bucle = true, salir = false;
		if(temp.equals("S")) {
			String nombrePrincesa;
			Princesadragon princesa = null;
			PrincesaDragonDao pdDao = new PrincesaDragonDao();
			do {
				nombrePrincesa = c.leerString("A que princesa quieres asignarle " + rosa.getNombreRosa() + "?(vacio para salir)").trim();
				princesa = (pdDao.exists(nombrePrincesa))?pdDao.buscarNombrePrincesa(nombrePrincesa) : null;
				if(nombrePrincesa.length() == 0) {
					salir = true;
				} else if(princesa == null) {
					c.escribirSL("Esa princesa no existe");
				} else {
					bucle = false;
				}
			} while(bucle && !salir);
			if(!salir) {
				Rosaconjuros rosaSecun = princesa.getRosaconjuros();
				if(rosaSecun != null) {
					c.escribirSL("Esa princesa ya tiene asignada la rosa " + rosaSecun.getNombreRosa());
					temp = c.leerString( "\nQuieres sobreescribir la rosa por " + rosa.getNombreRosa()+"? (S para sobreescribir):").toUpperCase();
					if(temp.equals("S")) {
						princesa.setRosaconjuros(rosa);
						pdDao.UpdatePrincesadragon(princesa);
						c.escribirSL("¡Se ha guardado '" + rosa.getNombreRosa() + "' en la princesa '"+ princesa.getNombrePrincesa() +"'!");
						c.escribirSL(rosaSecun.getNombreRosa() + " se ha quedado sin princesa asignada");
						recursivoPreguntaAsignarAPrincesaRosa(rosaSecun);
					} else {
						c.escribirSL("Operacion terminada");
					}
				} else {
					princesa.setRosaconjuros(rosa);
					pdDao.UpdatePrincesadragon(princesa);
					c.escribirSL("¡Se ha guardado '" + rosa.getNombreRosa() + "' en la princesa '"+ princesa.getNombrePrincesa() +"'!");
				}
			}
			else {
				c.escribirSL("Volviendo al menu...");
			}
		}
	}
}
