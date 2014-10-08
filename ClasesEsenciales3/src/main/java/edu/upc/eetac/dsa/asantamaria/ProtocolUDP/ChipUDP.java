package edu.upc.eetac.dsa.asantamaria.ProtocolUDP;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChipUDP {
	
	public ChipUDP() {

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