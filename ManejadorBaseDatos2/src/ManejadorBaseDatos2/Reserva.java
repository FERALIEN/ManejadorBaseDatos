package ManejadorBaseDatos2;

public class Reserva {
	private String nombre;
	private String telefono;
	private String fechaEvento;
	private String tipo;
	private String asistentes;
	private String tipoCocina;
	private String numeroJornadas;
	private String habitaciones;
	private String tipoMesa;
	private String comensalesMesa;
	
	public Reserva(String nombre, String telefono, String fechaEvento, String tipo, String asistentes,
			String tipoCocina, String numeroJornadas, String habitaciones, String tipoMesa, String comensalesMesa) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaEvento = fechaEvento;
		this.tipo = tipo;
		this.asistentes = asistentes;
		this.tipoCocina = tipoCocina;
		this.numeroJornadas = numeroJornadas;
		this.habitaciones = habitaciones;
		this.tipoMesa = tipoMesa;
		this.comensalesMesa = comensalesMesa;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(String asistentes) {
		this.asistentes = asistentes;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public String getNumeroJornadas() {
		return numeroJornadas;
	}

	public void setNumeroJornadas(String numeroJornadas) {
		this.numeroJornadas = numeroJornadas;
	}

	public String getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getTipoMesa() {
		return tipoMesa;
	}

	public void setTipoMesa(String tipoMesa) {
		this.tipoMesa = tipoMesa;
	}

	public String getComensalesMesa() {
		return comensalesMesa;
	}

	public void setComensalesMesa(String comensalesMesa) {
		this.comensalesMesa = comensalesMesa;
	}
	
	
}
