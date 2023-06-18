package com.ort.aplicacion;

import java.util.ArrayList;

import com.ort.entidades.CambiosFuncion;
import com.ort.entidades.Cliente;
import com.ort.entidades.Entrada;
import com.ort.entidades.Funcion;
import com.ort.entidades.Genero;
import com.ort.entidades.Sala;

public class CineORT {

	private ArrayList<Cliente> clientes;
	private ArrayList<Funcion> funciones;
	private ArrayList<Sala> salas;
	private ArrayList<CambiosFuncion> cambiosFuncion;

	public CineORT() {
		cargarEntidades();
	}

	/**
	 * Realiza la compra de una cantidad de butacas para una función para el cliente indicado.
	 * @param dni
	 * @param codigoFuncion
	 * @param butacas
	 * @return El resultado de la operación
	 */
	public ResultadoCompra comprarEntrada(String dni, String codigoFuncion, int butacas) {
		//TODO Considearar si el cliente tiene saldo suficiente
		ResultadoCompra resultado = ResultadoCompra.OK;
		Cliente cliente;
		Funcion funcion;
		Entrada entrada;

		cliente = obtenerCliente(dni);
		if (cliente != null) {
			funcion = obtenerFuncion(codigoFuncion);
			if (funcion != null) {
				entrada = funcion.comprar(butacas);
				if (entrada != null) {
					if (cliente.getSaldo() >= entrada.getPrecioTotal()) {
						cliente.agregarEntrada(entrada);
						cliente.setSaldo(cliente.getSaldo()-entrada.getPrecioTotal());
					}else {
						resultado = ResultadoCompra.SALDO_INSUFICIENTE;
					}
				} else {
					resultado = ResultadoCompra.FUNCION_SIN_CAPACIDAD;
				}
			} else {
				resultado = ResultadoCompra.FUNCION_INEXISTENTE;
			}
		} else {
			resultado = ResultadoCompra.CLIENTE_INEXISTENTE;
		}

		return resultado;
	}

	/**
	 * Cambia de sala a la función indicada
	 * @param codigoFuncion
	 * @param numeroSala
	 * @return El resultado de la operación
	 */
	public ResultadoCambiarSalaFuncion cambiarSalaFuncion(String codigoFuncion, int numeroSala) {
		//TODO Registrar el cambio de sala
		ResultadoCambiarSalaFuncion resultado = ResultadoCambiarSalaFuncion.OK;
		Funcion funcion;
		Sala sala;

		funcion = obtenerFuncion(codigoFuncion);
		if (funcion != null) {
			sala = obtenerSala(numeroSala);
			if (sala != null) {
				if (funcion.cambiarSala(sala)) {
					CambiosFuncion cambio = new CambiosFuncion(funcion, funcion.getSala(), sala);
					cambiosFuncion.add(cambio);
				} else {
					resultado = ResultadoCambiarSalaFuncion.CAPACIDAD_SALA_INSUFICIENTE;
				}
			} else {
				resultado = ResultadoCambiarSalaFuncion.SALA_INEXISTENTE;
			}
		} else {
			resultado = ResultadoCambiarSalaFuncion.FUNCION_INEXISTENTE;
		}

		return resultado;
	}
	
	/**
	 * Muestra por pantalla toda la información de la última entrada comprada 
	 * por el cliente indicado, incluyendo toda la información de la función y la sala.<p/>
	 * Casos contemplados:<br/>
	 * &nbsp;CLIENTE_INEXISTENTE: el cliente no existe en la base de clientes<br/>
	 * &nbsp;CLIENTE_SIN_ENTRADAS: el cliente no tiene entradas<br/>
	 * &nbsp;OK: la operación se realizó correctamente
	 * @param dni del cliente
	 * @return el resultado de la operación
	 */
	public ResultadoUltimaEntrada mostrarUltimaEntrada(String dni) {
		//TODO Completar!
		ResultadoUltimaEntrada resultado = ResultadoUltimaEntrada.OK;
		boolean hayCliente = false;
		for( Cliente cliente: clientes) {
			if (cliente.mismoCliente(dni)) {
				hayCliente = true;
				if(cliente.getEntradas().size() > 0) {
					int idxUltimaEntrada = cliente.getEntradas().size() - 1;
					Entrada ultimaEntrada = cliente.getEntradas().get(idxUltimaEntrada);
					System.out.println("Cliente ultima entrada: " + cliente + " Entrada: " + ultimaEntrada + " Función: " + ultimaEntrada.getFuncion() + " sala: " + ultimaEntrada.getFuncion().getSala());
				}else {
					resultado = ResultadoUltimaEntrada.CLIENTE_SIN_ENTRADAS;
				}
			}
		}
		if(!hayCliente) {
			resultado = ResultadoUltimaEntrada.CLIENTE_INEXISTENTE;
		}
		return resultado;
	}
	
	/**
	 * Devuelve una colección con los clientes que tengan un promedio de compra mayor o igual a la indicada.
	 * La compra promedio se define como el monto total de compras dividido la cantidad de entradas.
	 * @param compraPromedioMinima mínimo promedio de compra para determinar los clientes
	 * @return colección de clientes (puede ser una colección vacía si no hay ninguno)
	 */
	public ArrayList<Cliente> obtenerMejoresClientes(double compraPromedioMinima){
		//TODO Completar!
		ArrayList<Cliente> mejoresClientes = new ArrayList<Cliente>();
		for(Cliente cliente: clientes) {
			if(cliente.getEntradas().size() > 0) {
				double promedioCliente = cliente.getTotalCompras() / cliente.getEntradas().size();
				if (promedioCliente >= compraPromedioMinima) {
					mejoresClientes.add(cliente);
				}
			}
		}
		return mejoresClientes;
	}
	
	private Cliente obtenerCliente(String dni) {
		Cliente cliente = null;
		int pos = 0;

		while (pos < clientes.size() && !clientes.get(pos).mismoCliente(dni)) {
			pos++;
		}
		if (pos < clientes.size()) {
			cliente = clientes.get(pos);
		}
		return cliente;
	}

	private Funcion obtenerFuncion(String codigoFuncion) {
		Funcion funcion = null;
		int pos = 0;

		while (pos < funciones.size() && funcion == null) {
			if (funciones.get(pos).mismaFuncion(codigoFuncion)) {
				funcion = funciones.get(pos);
			} else {
				pos++;
			}
		}

		return funcion;
	}

	private Sala obtenerSala(int numeroSala) {
		Sala salaEncontrada = null;
		Sala sala;
		int cantidadSalas;
		int pos = 0;

		cantidadSalas = salas.size();
		while (pos < cantidadSalas && salaEncontrada == null) {
			sala = salas.get(pos);
			if (sala.mismaSala(numeroSala)) {
				salaEncontrada = sala;
			} else {
				pos++;
			}
		}

		return salaEncontrada;
	}

	private void cargarEntidades() {
		cargarClientes();
		cargarSalas();
		cargarFunciones();
	}

	private void cargarClientes() {
		clientes = new ArrayList<>();
		clientes.add(new Cliente("1111", "Juan", "Perez", Genero.MASCULINO));
		clientes.add(new Cliente("2222", "Maria", "Gomez", Genero.FEMENINO));
		clientes.add(new Cliente("3333", "Andy", "Muñoz", Genero.OTRO));
		clientes.add(new Cliente("4444", "Candelaria", "Garcia", Genero.FEMENINO));
		clientes.add(new Cliente("5555", "Silvia", "Gimenez", Genero.FEMENINO));
		clientes.add(new Cliente("6666", "Rick", "Sanchez", Genero.FEMENINO));

	}

	private void cargarSalas() {
		salas = new ArrayList<>();
		salas.add(new Sala(1, 100));
		salas.add(new Sala(2, 50));
		salas.add(new Sala(3, 25));
		salas.add(new Sala(4, 25));
	}

	private void cargarFunciones() {
		funciones = new ArrayList<>();

		funciones.add(new Funcion("FN001", "Avatar I", 150, 900, salas.get(0)));
		funciones.add(new Funcion("FN002", "Avatar II", 200, 1200, salas.get(0)));

		funciones.add(new Funcion("FN003", "Terminator I", 120, 850, salas.get(1)));
		funciones.add(new Funcion("FN004", "Terminator II", 120, 850, salas.get(1)));

		funciones.add(new Funcion("FN005", "Volver al futuro I", 120, 450, salas.get(2)));
		funciones.add(new Funcion("FN006", "Volver al futuro II", 110, 450, salas.get(2)));
		funciones.add(new Funcion("FN007", "Volver al futuro III", 100, 450, salas.get(3)));
	}

}
