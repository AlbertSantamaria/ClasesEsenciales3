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
 * Protocolo Horas TCP-no concurrente (Cliente)
 * Antes de presentar revisar IP etc..
 *
 */

@SuppressWarnings("serial")
public class ClientPH extends JFrame implements ActionListener, Runnable {

	JButton btnenviar;
	JButton btnenviar2;
	JButton btnsalir;
	JTextArea txtmensajes_rx;

	ServerSocket servidor = null;

	public ClientPH() {

		// Espacio de Respuestas
		txtmensajes_rx = new JTextArea();
		txtmensajes_rx.setBounds(10, 90, 450, 370);
		// .......

		btnenviar = new JButton("Formato completo");
		btnenviar.setBounds(10, 30, 170, 40);
		btnenviar.addActionListener(this);

		btnenviar2 = new JButton("Formato numerico");
		btnenviar2.setBounds(185, 30, 170, 40);
		btnenviar2.addActionListener(this);
		
		btnsalir = new JButton("Salir");
		btnsalir.setBounds(360, 30, 100, 40);
		btnsalir.addActionListener(this);

		add(btnenviar);
		add(btnenviar2);
		add(btnsalir);

		add(txtmensajes_rx);

		setLayout(null);
		setSize(470, 470);
		setVisible(true);

		Thread hilo = new Thread(this);
		hilo.start();
	}

	public static void main(String[] args) {
		new ClientPH();

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
		if (arg0.getSource() == btnenviar) {
			try {
				Socket cli = new Socket("127.0.0.1", 9001);

				Peticion peticion=new Peticion();
				peticion.setVal(true);
				
				ObjectOutputStream flujo_objetos = new ObjectOutputStream(
						cli.getOutputStream());
				
				flujo_objetos.writeObject(peticion);

				cli.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == btnenviar2) {
			try {
				Socket cli = new Socket("127.0.0.1", 9001);


				Peticion peticion=new Peticion();
				peticion.setVal(false);
				
				ObjectOutputStream flujo_objetos = new ObjectOutputStream(
						cli.getOutputStream());
				
				flujo_objetos.writeObject(peticion);

				cli.close();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void run() {

		try {
			// Apertura Socket
			servidor = new ServerSocket(9003);
			Socket serv;

			Peticion respuesta_rx;
			
			while (true) {
				// Acepta cx entrantes
				serv = servidor.accept();

				// Lee Objeto
				ObjectInputStream flujo_entrada = new ObjectInputStream(
						serv.getInputStream());
				respuesta_rx = new Peticion();

				// Hay que parsearlo para recibirlo en la instancia local
				respuesta_rx = (Peticion) flujo_entrada.readObject();

				// pone mensaje en el formulario
				txtmensajes_rx.append("\n Respuesta Server: " + respuesta_rx.getMensaje());

				// cierra
				serv.close();


			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}