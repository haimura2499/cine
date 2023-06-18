package com.ort.entidades;

public class CambiosFuncion {
	
	private Funcion funcion;
	private Sala salaAnterior;
	private Sala salaNueva;
	
	public CambiosFuncion(Funcion funcion, Sala salaAnterior, Sala salaNueva) {
		this.funcion = funcion;
		this.salaAnterior = salaAnterior;
		this.salaNueva = salaNueva;
	}
	
}
