package edu.upc.eetac.dsa.asantamaria.ProtocolUDP;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
 * Protocolo Horas TCP-no concurrente (Cliente)
 * Antes de presentar revisar IP etc..
 *
 */

public class ClienteUDP implements Runnable {

	public ClienteUDP() {

		Thread hilo = new Thread(this);
		hilo.start();
	}

	public static void main(String[] args) {
		new ClienteUDP();

	}

	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket();

			// send request
			byte[] buf = new byte[256];

			System.out.println("Escoja tipo de formato");
			System.out.println("");
			System.out.println("1) Completo");
			System.out.println("2) Numerico");
			System.out.println("");
			System.out.print("->");

			Scanner sc = new Scanner(System.in);
			int op = sc.nextInt();

			PeticionUDP send;

			System.out.println(op);
			
			if (op == 1) {
				send = new PeticionUDP("", true);
			}
			if (op == 2) {
				send = new PeticionUDP("", false);

			} else {
				System.out.println("No seleccionaste correctamente. Se solicitara formato por defecto:");	
				System.out.println("");
				
				send = new PeticionUDP("", true);
			}

			// Convierte Peticion en Byte Array
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bytes);
			oos.writeObject(send); // this es de tipo DatoUdp
			oos.close();
			buf = bytes.toByteArray();

			InetAddress address = InetAddress.getByName("localhost");

			// Mete array en packet y envia
			DatagramPacket packet = new DatagramPacket(buf, buf.length,
					address, 4001);
			socket.send(packet);
			System.out.println("[]-->Enviando peticion al servidor");
			// get response
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// convierte rx en objeto

			// display response
			String received = new String(packet.getData(), 0,
					packet.getLength());
			System.out.println("[]<--Recibido del servidor: " + received);

			socket.close();

		} catch (Exception e) {

		}
	}
}
