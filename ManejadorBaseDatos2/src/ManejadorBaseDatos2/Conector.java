package ManejadorBaseDatos2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conector {
	//Metodo para iniciar la conexion
	private static Connection iniciarConexion() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas", "root", "admin");
			 System.out.println("Conexión a la BD");
			 return con;
			 }
		 catch (Exception e) {
		 System.out.println("Error en conexión ");
		 }
		return null;
	}
	
	//Metodo para ejecutar la sentencia de escritura
	public static void ejecutarSentenciaEscribir(Reserva reserva) {
		Connection con = iniciarConexion();
		try {
			Statement st = con.createStatement();
			ResultSet rs;
			st.execute("INSERT INTO reserva (nombre, telefono, fecha_evento, tipo_evento, n_personas, tipo_cocina, n_jornadas, n_habitaciones, tipo_mesa, n_comensales) values " + 
					"('"+ reserva.getNombre()+"','"+ reserva.getTelefono()+"','"+ reserva.getFechaEvento()+"','"+ reserva.getTipo()+"','"+ reserva.getAsistentes()+"','"+ reserva.getTipoCocina()+"','"+ reserva.getNumeroJornadas()+"','"+ reserva.getHabitaciones()+"','"+ reserva.getTipoMesa()+"','"+ reserva.getComensalesMesa()+"');");
			 }
				 catch (SQLException e)
				 {
				 System.out.println("Error al mostrar");
				 } 
		cerrarConexion(con);
	}
	
	//Metodo para ejecutar la sentencia de lectura
		public static Reserva ejecutarSentenciaLectura(Reserva reserva) {
			Connection con = iniciarConexion();
			try {
				Statement st = con.createStatement();
				ResultSet rs;
				rs = st.executeQuery("select * from reserva;");
				 while(rs.next()) {
					 reserva.setNombre(rs.getString(1));
					 reserva.setTelefono(rs.getString(2));
					 reserva.setFechaEvento(rs.getString(3));
					 reserva.setTipo(rs.getString(4));
					 reserva.setAsistentes(rs.getString(5));
					 reserva.setTipoCocina(rs.getString(6));
					 reserva.setNumeroJornadas(rs.getString(7));
					 reserva.setHabitaciones(rs.getString(8));
					 reserva.setTipoMesa(rs.getString(9));
					 reserva.setComensalesMesa(rs.getString(10));
				 }
				 }
					 catch (SQLException e)
					 {
					 System.out.println("Error al mostrar");
					 } 
			cerrarConexion(con);
			return reserva;
		}
	
	//Metodo para cerrar la conexion
	private static void cerrarConexion(Connection con) {
		try {
			 con.close();
			 System.out.println("Conexión cerrada");
			 }
		catch (SQLException e) {
		System.out.println("Error al cerrar conexión");
		} 
	}
}
