package com.ort.entidades;

public class Entrada {
	private Funcion funcion;
	private int cantidad;
	private double precioTotal;

	public Entrada(Funcion funcion, int cantidad) {
		this.funcion = funcion;
		this.cantidad = cantidad;
		this.precioTotal = calcularPrecioTotal(this.cantidad, this.funcion.getPrecioButaca());
	}

	private double calcularPrecioTotal(int cantidad, double precioButaca) {
		return cantidad * precioButaca;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	@Override
	public String toString() {
		return "cantidad=" + cantidad + ", precioTotal=" + precioTotal;
	}
	
	
}
