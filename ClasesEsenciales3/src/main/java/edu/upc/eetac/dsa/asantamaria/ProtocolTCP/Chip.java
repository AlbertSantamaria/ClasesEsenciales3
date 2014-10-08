package edu.upc.eetac.dsa.asantamaria.ProtocolTCP;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Protocolo Horas TCP-no concurrente (clases aux)
 * Antes de presentar revisar IP etc..
 *
 */

public class Chip {

	public Chip() {

	}

	String getHora(boolean op) {

		if (op) {

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"EEEE d MMMM 'de' yyyy, HH:mm:ss");

			return dateFormat.format(new Date());

		} else {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat(
					"dd/MM/yy HH:mm:ss");

			return dateFormat2.format(new Date());
		}

	}

}
