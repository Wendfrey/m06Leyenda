package Antonio.Leyenda;

import leyenda.view.Consola;

public abstract class Menu {
	
	private final static int SALIR = 0;
	
	public abstract String mostrarOpciones();
	public abstract int ejecutarOpcion(int opcion);
	
	public int leerOpcion()
	{
		Consola consola=Consola.getSingletonInstance();
		int opcion;
		String respuesta;
		
		opcion = SALIR;
		respuesta = consola.leerString("");
		try {		
			opcion = Integer.parseInt(respuesta);
		} 
		catch (Exception e) {
			consola.escribirSL("ERROR: Opcion no valida");
		}
		return opcion;
	}
	
	public void bucle() {
		Consola consola=Consola.getSingletonInstance();
		int opcion = SALIR;
		
		do {
	    	consola.escribir(this.mostrarOpciones());
			opcion = this.leerOpcion();
			opcion = this.ejecutarOpcion(opcion);
		} while (opcion != SALIR);
	}
	
	public int opcionSalir() {
		return SALIR;
	}

}
