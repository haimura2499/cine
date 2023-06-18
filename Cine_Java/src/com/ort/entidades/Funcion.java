package com.ort.entidades;

public class Funcion {
	private String codigo;
	private String titulo;
	private int duracion;
	private double precioButaca;
	private int butacasDisponibles;

	private Sala sala;

	public Funcion(String codigo, String titulo, int duracion, double precioButaca, Sala sala) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.duracion = duracion;
		this.precioButaca = precioButaca;
		this.sala = sala;
		butacasDisponibles = sala.getCapacidad();
	}
	
	public Sala getSala() {
		return sala;
	}

	public double getPrecioButaca() {
		return precioButaca;
	}

	public boolean mismaFuncion(String codigo) {
		return this.codigo.equals(codigo);
	}

	public Entrada comprar(int butacas) {
		Entrada entrada = null;

		if (cantidadDisponible(butacas)) {
			butacasDisponibles -= butacas;
			entrada = new Entrada(this, butacas);
		}
		return entrada;
	}

	private boolean cantidadDisponible(int butacas) {
		return butacasDisponibles >= butacas;
	}

	public boolean cambiarSala(Sala sala) {
		boolean resultado = false;
		if (capacidadSalaSuficiente(sala.getCapacidad())) {
			butacasDisponibles = recalcularButacasDisponibles(sala.getCapacidad());
			this.sala = sala;
			resultado = true;
		}

		return resultado;
	}

	private int recalcularButacasDisponibles(int nuevaCapacidad) {
		int butacasVendidas;

		butacasVendidas = getButacasVendidas();
		return nuevaCapacidad - butacasVendidas;
	}

	private boolean capacidadSalaSuficiente(int capacidadNuevaSala) {
		int butacasVendidas;

		butacasVendidas = getButacasVendidas();
		return butacasVendidas <= capacidadNuevaSala;
	}

	private int getButacasVendidas() {
		return sala.getCapacidad() - butacasDisponibles;
	}

	@Override
	public String toString() {
		return "codigo=" + codigo + ", titulo=" + titulo + ", duracion=" + duracion + ", precioButaca="
				+ precioButaca + ", butacasDisponibles=" + butacasDisponibles + ", sala=" + sala;
	}

	

}
