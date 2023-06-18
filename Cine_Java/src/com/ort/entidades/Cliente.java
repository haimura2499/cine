package com.ort.entidades;

import java.util.ArrayList;

public class Cliente {
	private String dni;
	private String nombre;
	private String apellido;
	private Genero genero;
	private Double saldo;

	private ArrayList<Entrada> entradas;

	public Cliente(String dni, String nombre, String apellido, Genero genero) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.setSaldo(10000.0);
		entradas = new ArrayList<>();
	}

	public boolean mismoCliente(String dni) {
		return this.dni.equals(dni);
	}

	public void agregarEntrada(Entrada entrada) {
		this.entradas.add(entrada);
	}
	
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public double getTotalCompras() {
		double totalCompras = 0;

		for (Entrada entrada : entradas) {
			totalCompras += entrada.getPrecioTotal();
		}

		return totalCompras;
	}
	
	@Override
	public String toString() {
		return "dni=" + dni + " nombre=" + nombre + " apellido=" + apellido + " genero=" + genero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
