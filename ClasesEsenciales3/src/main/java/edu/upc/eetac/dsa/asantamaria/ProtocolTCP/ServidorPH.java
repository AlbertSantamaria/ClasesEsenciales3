package edu.upc.eetac.dsa.asantamaria.ProtocolTCP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/*
 * Protocolo Horas TCP-no concurrente (Server)
 * Antes de presentar revisar IP etc..
 *
 */

@SuppressWarnings("serial")
public class ServidorPH extends JFrame implements Runnable, ActionListener {

	JTextArea mensajes;

//	String pregunta = "¿Que hora es?";

	ServerSocket servidor = null;

	// Para asegurar cerrar puertos
	JButton btnsalir;

	public ServidorPH() {

		btnsalir = new JButton("Salir");
		btnsalir.setBounds(10, 10, 430, 30);
		btnsalir.addActionListener(this);

		mensajes = new JTextArea();
		mensajes.setBounds(10, 40, 430, 400);
		add(mensajes);
		add(btnsalir);

		setLayout(null);
		setSize(450, 450);
		setVisible(true);

		Thread hilo = new Thread(this);
		// .start llama al run() creado
		hilo.start();
	}

	public static void main(String[] args) {

		new ServidorPH();

	}

	public void run() {
		try {
			// Apertura Socket
			servidor = new ServerSocket(9001);
			Socket cli, client_send;
			Peticion peticion_rx;

			while (true) {
				// Acepta cx entrantes
				cli = servidor.accept();
				// mensajes.append("\n Servidor conectado" );

				// Lee Objeto
				ObjectInputStream flujo_entrada = new ObjectInputStream(
						cli.getInputStream());
				peticion_rx = new Peticion();

				// Hay que parsearlo para recibirlo en la instancia local

				peticion_rx = (Peticion) flujo_entrada.readObject();

				// pone mensaje en el formulario
				mensajes.append(cli.getInetAddress() + " Pregunta: "
						+ peticion_rx.getMensaje() + " modo "
						+ peticion_rx.getTipo()+"\n");

				// Respondiendo
				client_send = new Socket(cli.getInetAddress(), 9003);
				ObjectOutputStream flujo_respuesta = new ObjectOutputStream(
						client_send.getOutputStream());

				Peticion peticion_tx = new Peticion();
				Chip chip = new Chip();

				peticion_tx.setMensaje(chip.getHora(peticion_rx.getVal()));

				flujo_respuesta.writeObject(peticion_tx);
				client_send.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == btnsalir) {

			try {
				servidor.close();
				dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}