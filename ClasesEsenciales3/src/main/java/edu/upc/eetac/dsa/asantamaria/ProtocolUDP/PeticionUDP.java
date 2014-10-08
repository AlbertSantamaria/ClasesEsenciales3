package edu.upc.eetac.dsa.asantamaria.ProtocolUDP;

import java.io.Serializable;

/*
 * Protocolo Horas TCP-no concurrente (clases aux)
 * Antes de presentar revisar IP etc..
 *
 */

public class PeticionUDP implements Serializable{



boolean val;
String msg = "¿Que hora es?";

public PeticionUDP() {

}

public PeticionUDP(String m, boolean b) {

	this.setMensaje(m);
	this.setVal(b);
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
