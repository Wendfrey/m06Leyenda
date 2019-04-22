package Antonio.Leyenda;

import leyenda.dao.EspadaDao;
import leyenda.dao.PrincesaDragonDao;
import leyenda.dao.RosaDao;
import leyenda.dao.TienesDao;
import leyenda.model.Caballero;
import leyenda.model.Espada;
import leyenda.model.Princesadragon;
import leyenda.model.Rosaconjuros;
import leyenda.model.Tiene;
import leyenda.model.TieneId;
import leyenda.view.Consola;

public class MenuPrincipal extends Menu {

	@Override
	public String mostrarOpciones() {
		String cadena;
		cadena = "MENU PRINCIPAL LEYENDA\n";
		cadena += "_____________________________\n";
		cadena += "Salir				-> 0\n";
		cadena += "Añadir nueva Rosa de Conjuros	-> 1\n";
		cadena += "Elimina Espada			-> 2\n";
		cadena += "Modificar Caballeros de Dragon	-> 3\n";
		cadena += "Mostrar Espadas de Caballero	-> 4\n";
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
			case 2:
				resultado = 2;
				BorrarEspada();
				break;
			case 3:
				resultado = 3;
				ModificarEnfrentamientoDragon();
				break;
			case 0:
				resultado = 0;
				Consola.getSingletonInstance().escribirSL("Adiós!");
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
		String temp = c.leerString("Quieres asignarle una princesa a la rosa " + rosa.getNombreRosa() + "? (S para asignar)").trim().toUpperCase();
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
					temp = c.leerString( "\nQuieres sobreescribir la rosa por " + rosa.getNombreRosa()+"? (S para sobreescribir):").trim().toUpperCase();
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
	
	private void BorrarEspada() {
		Consola c = Consola.getSingletonInstance();
		EspadaDao eDao = new EspadaDao();
		Espada espada;
		String nombreEspada;
		boolean bucle = true, salir = false;
		
		do{
			nombreEspada = c.leerString("Escribe el nombre de la Espada a borrar (vacio para salir): ").trim();
			if(nombreEspada.length() == 0) {
				salir = true;
			} else if(nombreEspada.length() > 25) {
				c.escribirSL("El nombre " + nombreEspada + " es demasiado largo.");
			} else if(!eDao.exists(nombreEspada)){
				c.escribirSL("No existe una espada con el nombre " + nombreEspada + ".");
			} else {
				bucle = false;
			}
		} while(bucle && !salir);
		
		if(!salir) {
			espada = eDao.selectEspada(nombreEspada);
			if(espada.getTienes().size() > 0) {
				c.escribirSL("La espada "+ espada.getNombreEspada() +" tiene " + espada.getTienes().size()+ " conexiones con otras tablas.");
				c.escribirSL("Si continuas las conexiones tambien se borraran.");
				String temp = c.leerString("Quieres continuar? (S para continuar):").trim().toUpperCase();
				if(temp.equals("S")) {
					TienesDao tDao = new TienesDao();
					for(Tiene t: espada.getTienes()) {
						Tiene tt = new Tiene();
						tt.setId(t.getId());
						tDao.deleteTiene(tt);
					}
					eDao.deleteEspada(espada);
					c.escribirSL("¡Se ha borrado la espada " + espada.getNombreEspada()+ " y sus conexiones!");
				} else {
					c.escribirSL("Operacion cancelada. Volviendo a menú...");
				}
			}
			else {
				eDao.deleteEspada(espada);
				c.escribirSL("¡Se ha borrado la espada " + espada.getNombreEspada()+ "!");
			}
		} else {
			c.escribirSL("Operacion cancelada. Volviendo a menú...");
		}
	}
	
	public void ModificarEnfrentamientoDragon() {
		Consola c = Consola.getSingletonInstance();
		PrincesaDragonDao pdDao =  new PrincesaDragonDao();
		Princesadragon dragon;
		String nombreDragon;
		boolean bucle = true, salir = false;
		
		do{
			nombreDragon = c.leerString("Escribe el nombre del Dragon a modificar (vacio para salir): ").trim();
			if(nombreDragon.length() == 0) {
				salir = true;
			} else if(nombreDragon.length() > 25) {
				c.escribirSL("El nombre " + nombreDragon + " es demasiado largo.");
			} else if(!pdDao.existsDragon(nombreDragon)){
				c.escribirSL("No existe un dragon con el nombre " + nombreDragon + ".");
			} else {
				bucle = false;
			}
		} while(bucle && !salir);
		
		if(!salir) {
			dragon = pdDao.buscarNombreDragon(nombreDragon);
			new MenuModificarCaballerosEnfrentados(dragon).bucle();
		}
	}
}
