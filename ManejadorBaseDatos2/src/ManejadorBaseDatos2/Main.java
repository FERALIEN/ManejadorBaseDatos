package ManejadorBaseDatos2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<String> fichero = new ArrayList<String>();
		Scanner teclado = new Scanner(System.in);
		Reserva reserva = new Reserva("", "", "", "", "", "", "", "", "", "");
		int[] opciones = new int[2];
		
		opciones = mostrarOpciones(teclado);
		fichero = leerDatos(teclado, fichero, opciones[0]);
		reserva = Manejador.guardarReserva(fichero, reserva);
		
		if(opciones[0] == 3) {
			reserva = leerDatosSql(reserva);
		}
		escribirDatos(reserva, fichero, opciones[1]);
	}
	
	public static int[] mostrarOpciones(Scanner teclado) {
		int[] opciones = new int[2];
		System.out.println("Leer fichero XML(1) | Leer fichero JSON(2) | Leer tabla SQL(3)");
		opciones[0] = teclado.nextInt();
		System.out.println("Escribir fichero XML(1) | Escribir fichero JSON(2) | Insertar tabla SQL(3)");
		opciones[1] = teclado.nextInt();
		return opciones;
	}
	
	public static ArrayList<String> leerDatos(Scanner teclado, ArrayList<String> fichero, int opcion) throws IOException {
		String nombreFichero = "";
		if(opcion !=3) {
		System.out.println("Introduzca el nombre del fichero sin extension");
		nombreFichero = teclado.next();
		}
		
		if(opcion == 1) {fichero = Manejador.leerXml(fichero, nombreFichero);}
		if(opcion == 2) {fichero = Manejador.leerJson(fichero, nombreFichero);}
		return fichero;
	}
	
	public static Reserva leerDatosSql(Reserva reserva) {
		reserva = Conector.ejecutarSentenciaLectura(reserva);
		return reserva;
	}
	
	public static void escribirDatos(Reserva reserva, ArrayList<String> fichero, int opcion) throws IOException {
		if(opcion == 1) {Manejador.escribirXml(reserva);}
		if(opcion == 2) {Manejador.escribirJson(reserva);}
		if(opcion == 3) {Conector.ejecutarSentenciaEscribir(reserva);}
	}

}
