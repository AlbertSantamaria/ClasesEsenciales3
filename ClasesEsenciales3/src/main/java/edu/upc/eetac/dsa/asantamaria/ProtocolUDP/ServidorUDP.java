package edu.upc.eetac.dsa.asantamaria.ProtocolUDP;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * Protocolo Horas UDP-no concurrente (Server)
 * Antes de presentar revisar IP etc..
 *
 */

public class ServidorUDP implements Runnable {

	DatagramSocket socket = null;
	BufferedReader in_buffer = null;
	int numpeticion = 0;

	public ServidorUDP() {

		Thread hilo = new Thread(this);
		// .start llama al run() creado
		hilo.start();
	}

	public static void main(String[] args) {

		new ServidorUDP();

	}

	public void run() {
		try {
			// Apertura Socket
			socket = new DatagramSocket(4001);
			while (true) {
				byte[] buf = new byte[256];
				PeticionUDP rx = new PeticionUDP();

				// receive request
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				numpeticion++;

				// Convertir en Objeto

				ByteArrayInputStream byteArray = new ByteArrayInputStream(buf);
				ObjectInputStream ois = new ObjectInputStream(byteArray);
				rx = (PeticionUDP) ois.readObject();

				// Lee Objeto
				if (rx.getVal()) {
					System.out.println("[]<--Recibida peticion nº"
							+ numpeticion + " de " + packet.getAddress()
							+ " hora en formato completo");
				} else {
					System.out.println("[]<--Recibida peticion nº"
							+ numpeticion + " de " + packet.getAddress()
							+ " hora en formato numerico");
				}

				// Respondiendo

				String respuesta = null;
				ChipUDP chip = new ChipUDP();
				respuesta = chip.getHora(rx.getVal());

				// Convertir objeto a bytes[]
				// ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				// ObjectOutputStream oos = new ObjectOutputStream (bytes);
				// oos.writeObject(this); // this es de tipo DatoUdp
				buf = respuesta.getBytes();

				// Envio
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
				System.out.println("[]-->Enviada respuesta a peticion nº"+numpeticion);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		socket.close();
	}

}