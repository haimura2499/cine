package com.ort.entidades;

public class Sala {
	private int numero;
	private int capacidad;
	
	public Sala(int numero, int capacidad) {
		this.numero = numero;
		this.capacidad = capacidad;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public boolean mismaSala(int numero) {
		return this.numero == numero;
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", capacidad=" + capacidad + "]";
	}
	
	
}
