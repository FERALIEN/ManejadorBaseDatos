package ManejadorBaseDatos2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manejador {

	public static ArrayList<String> leerXml(ArrayList<String> fichero, String nombreFichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nombreFichero + ".xml"));
		String linea = "";
		int tipoEvento = 0;
		
		while ((linea = br.readLine()) != null) {
			if (linea.contains("<reserva>") || linea.contains("</reserva>")) {fichero.add(linea);}
        	if(linea.contains("<tipo>Jornada</tipo>")) {tipoEvento = 0;}
        	if(linea.contains("<tipo>Congreso</tipo>")) {tipoEvento = 1;}
        	if(linea.contains("<tipo>Banquete</tipo>")) {tipoEvento = 2;}
        	
        	if (linea.contains("<nombre>") || linea.contains("<fechaEvento>") || linea.contains("<tipo>") || linea.contains("<asistentes>") || linea.contains("<tipoCocina>")) {fichero.add(linea);}
        	if (linea.contains("<numeroJornadas>") && tipoEvento == 1) {fichero.add(linea);}
        	if (linea.contains("<habitaciones>") && tipoEvento == 1) {fichero.add(linea);}
        	if (linea.contains("<tipoMesa>") && tipoEvento == 2) {fichero.add(linea);}
        	if (linea.contains("<comensalesMesa>") && tipoEvento == 2) {fichero.add(linea);}
		}
		br.close();
		return fichero;
	}
	
	public static ArrayList<String> leerJson(ArrayList<String> fichero, String nombreFichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nombreFichero + ".json"));
		String linea = "";
		int tipoEvento = 0;
		
		while ((linea = br.readLine()) != null) {
			if (linea.contains("{")|| linea.contains("}")) {fichero.add(linea);}
        	if(linea.contains("\"tipo\": \"Jornada\"")) {tipoEvento = 0;}
        	if(linea.contains("\"tipo\": \"Congreso\"")) {tipoEvento = 1;}
        	if(linea.contains("\"tipo\": \"Banquete\"")) {tipoEvento = 2;}
        	
        	if (linea.contains("\"nombre\"") || linea.contains("\"fechaEvento\"") || linea.contains("\"tipo\"") || linea.contains("\"asistentes\"") || linea.contains("\"tipoCocina\"")) {fichero.add(linea);}
        	if (linea.contains("\"numeroJornadas\"") && tipoEvento == 1) {fichero.add(linea);}
        	if (linea.contains("\"habitaciones\"") && tipoEvento == 1) {fichero.add(linea);}
        	if (linea.contains("\"tipoMesa\"") && tipoEvento == 2) {fichero.add(linea);}
        	if (linea.contains("\"comensalesMesa\"") && tipoEvento == 2) {fichero.add(linea);}
		}
	    br.close();
		return fichero;
	}
	
	public static Reserva guardarReserva(ArrayList<String> fichero, Reserva reserva) {
		int tipo = 0;
		for (String s : fichero) {
			if(s.contains("<reserva>")) {tipo = 1;}
			if (s.contains("nombre")) {reserva.setNombre(separador(tipo, s));}
			if (s.contains("telefono")) {reserva.setTelefono(separador(tipo, s));}
			if (s.contains("fechaEvento")) {reserva.setFechaEvento(separador(tipo, s));}
			if (s.contains("\"tipo\"") || s.contains("<tipo>")) {reserva.setTipo(separador(tipo, s));}
			if (s.contains("asistentes")) {reserva.setAsistentes(separador(tipo, s));}
			if (s.contains("tipoCocina")) {reserva.setTipoCocina(separador(tipo, s));}
			if (s.contains("numeroJornadas")) {reserva.setNumeroJornadas(separador(tipo, s));}
			if (s.contains("habitaciones")) {reserva.setHabitaciones(separador(tipo, s));}
			if (s.contains("tipoMesa")) {reserva.setTipoMesa(separador(tipo, s));}
			if (s.contains("comensalesMesa")) {reserva.setComensalesMesa(separador(tipo, s));}
		}
		return reserva;
	}
	
	public static String separador(int tipo, String i) {
		String datos = "";
		String[] texto;
		//para leer json
		if(tipo == 0) {
			texto = i.split(":");
			datos = texto[1];
			datos = datos.replace(" \"", "");
			datos = datos.replace("\"", "");
			datos = datos.replace(",", "");
		}
		//para leer xml
		if(tipo == 1) {
			 String s = i;
			 Pattern p = Pattern.compile("\\>.*?\\<");
			 Matcher m = p.matcher(s);
			 if(m.find()) {datos = (String) (m.group().subSequence(1, m.group().length()-1));};
		}
		return datos;
	}
	
	public static void escribirXml(Reserva reserva) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("ficheroSalida.xml"));
		if (!reserva.getNombre().isEmpty()) {bw.write("<reserva>" + "\n" + "<nombre>" + reserva.getNombre() + "</nombre>" + "\n");}
		if (!reserva.getTelefono().isEmpty()) {bw.write("<telefono>" + reserva.getTelefono() + "</telefono>" + "\n");}
		if (!reserva.getFechaEvento().isEmpty()) {bw.write("<fechaEvento>" + reserva.getFechaEvento() + "</fechaEvento>" + "\n");}
		if (!reserva.getTipo().isEmpty()) {bw.write("<tipo>" + reserva.getTipo() + "</tipo>" + "\n");}
		if (!reserva.getAsistentes().isEmpty()) {bw.write("<asistentes>" + reserva.getAsistentes() + "</asistentes>" + "\n");}
		if (!reserva.getTipoCocina().isEmpty()) {bw.write("<tipoCocina>" + reserva.getTipoCocina() + "</tipoCocina>" + "\n");}
		if (!reserva.getNumeroJornadas().isEmpty()) {bw.write("<numeroJornadas>" + reserva.getNumeroJornadas() + "</numeroJornadas>" + "\n");}
		if (!reserva.getHabitaciones().isEmpty()) {bw.write("<habitaciones>" + reserva.getHabitaciones() + "</habitaciones>" + "\n");}
		if (!reserva.getTipoMesa().isEmpty()) {bw.write("<tipoMesa>" + reserva.getTipoMesa() + "</tipoMesa>" + "\n");}
		if (!reserva.getComensalesMesa().isEmpty()) {bw.write("<comensalesMesa>" + reserva.getComensalesMesa() + "</comensalesMesa>" + "\n" + "</reserva>");}
		bw.close();
	}
	
	public static void escribirJson(Reserva reserva) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("ficheroSalida.json"));
		if (!reserva.getNombre().isEmpty()) {bw.write("{"+"\n"+"\"reserva\":{"+"\n" + "\"nombre\": \"" + reserva.getNombre() + "\"," + "\n");}
		if (!reserva.getTelefono().isEmpty()) {bw.write("\"telefono\": \"" + reserva.getTelefono() + "\"," + "\n");}
		if (!reserva.getFechaEvento().isEmpty()) {bw.write("\"fechaEvento\": \"" + reserva.getFechaEvento() + "\"," + "\n");}
		if (!reserva.getTipo().isEmpty()) {bw.write("\"tipo\": \"" + reserva.getTipo() + "\"," + "\n");}
		if (!reserva.getAsistentes().isEmpty()) {bw.write("\"asistentes\": \"" + reserva.getAsistentes() + "\"," + "\n");}
		if (!reserva.getTipoCocina().isEmpty()) {bw.write("\"tipoCocina\": \"" + reserva.getTipoCocina() + "\"," + "\n");}
		if (!reserva.getNumeroJornadas().isEmpty()) {bw.write("\"numeroJornadas\": \"" + reserva.getNumeroJornadas() + "\"," + "\n");}
		if (!reserva.getHabitaciones().isEmpty()) {bw.write("\"habitaciones\": \"" + reserva.getHabitaciones() + "\"," + "\n");}
		if (!reserva.getTipoCocina().isEmpty()) {bw.write("\"tipoMesa\": \"" + reserva.getTipoMesa() + "\"," + "\n");}
		if (!reserva.getComensalesMesa().isEmpty()) {bw.write("\"comensalesMesa\": \"" + reserva.getComensalesMesa() + "\"" + "\n"+"}"+"\n"+"}");}
		bw.close();
	}
	
}
