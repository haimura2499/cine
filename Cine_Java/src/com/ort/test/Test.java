package com.ort.test;

import java.util.ArrayList;

import com.ort.aplicacion.CineORT;
import com.ort.entidades.Cliente;

public class Test {

	public static void main(String[] args) {
		CineORT aplicacion = new CineORT();
		
		aplicacion.comprarEntrada("1111", "FN001", 2); 
		aplicacion.comprarEntrada("1111", "FN007", 3);
		
		aplicacion.comprarEntrada("2222", "FN004", 2);
		aplicacion.comprarEntrada("2222", "FN002", 1);
		
		aplicacion.comprarEntrada("3333", "FN007", 4);
		aplicacion.comprarEntrada("3333", "FN006", 5);
		
		aplicacion.comprarEntrada("4444", "FN003", 1);
		aplicacion.comprarEntrada("5555", "FN005", 4);
		
		// Se espera que se muestren los clientes 1111, 3333, 5555
		ArrayList<Cliente> mejoresClientes = aplicacion.obtenerMejoresClientes(1500);
		for(Cliente cliente : mejoresClientes) {
			System.out.println(cliente);
		}
		
		// Se espera que se muestre 1 butaca para la función Avatar II en la sala 1
		aplicacion.mostrarUltimaEntrada("2222");
		
		// Se espera que el resultado no sea satisfactorio por saldo insuficiente
		System.out.println(aplicacion.comprarEntrada("6666", "FN002", 90));

	}

}
