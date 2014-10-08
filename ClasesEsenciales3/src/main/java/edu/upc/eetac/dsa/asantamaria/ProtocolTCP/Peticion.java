package edu.upc.eetac.dsa.asantamaria.ProtocolTCP;

import java.io.Serializable;

	/*
	 * Protocolo Horas TCP-no concurrente (clases aux)
	 * Antes de presentar revisar IP etc..
	 *
	 */

public class Peticion implements Serializable{
	
	private static final long serialVersionUID = 1502356447687865427L;
	
	boolean val;
	String msg = "¿Que hora es?";

	public Peticion() {

	}

	boolean getVal() {
		return val;
	}
	String getTipo() {
		if(val){
			return "tipo 1";
			
		}else{
			return "tipo 2";}

	}

	void setVal(boolean val) {
		this.val = val;
	}

	String getMensaje() {
		return msg;
	}

	void setMensaje(String mensaje) {

		msg = mensaje;
	}

}
