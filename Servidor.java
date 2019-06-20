import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.io.IOException;

public class Servidor extends JFrame implements Runnable {

	private JPanel panel;
	private TextArea texto;
	private JButton boton;

	public Servidor(){
		//metodos de la ventana
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("Servidor");
		setLocationRelativeTo(null);

		//instanciar los elementos de la ventana
		panel = new JPanel();
		texto = new TextArea();
		boton = new JButton("Enviar");

		//se crea la accion realizada por el boton
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("funciona");
			}
		});


		//personalizar los elementos de la ventana
		panel.setSize(500,500);
		panel.setBackground(Color.gray);
		texto.setColumns(100);

		//añadirlos a la ventana
		panel.add(texto);
		panel.add(boton);
		this.add(panel);

		//se crea un hilo(subproceso de ejecucion) que se iniciara
		//y hara todo lo que contenga el metodo run siempre esta activo
		//Thread(This) este constructor recibe un 'Runnable' , para esto es implements Runnable
		Thread hilo = new Thread(this);
		hilo.start();


	}

	public void run(){

		try{

			//se crea el servidor
			ServerSocket serverS = new ServerSocket(8888);

			//el servidor siempre estara esperando peticiones de clientes por eso el while(true)
			//tambien por esto es necesario el hilo (Thread)
			while(true){
				//acepta las peticiones de los servidores (eso pienso no tan seguro)
				Socket socket = serverS.accept();

				//se crea el stream (flujo) de entrada que estara en el socket
				DataInputStream entrada = new DataInputStream(socket.getInputStream());
				
				//se recive el mensaje y se escribe en el textArea
				String mensaje = "";
				mensaje = entrada.readUTF();
				texto.setText(mensaje);

				//se cierra la peticion
				socket.close();

			}
		}catch(IOException e1){
			JOptionPane.showMessageDialog(null,e1.toString());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e.toString());
		}
	}

	public static void main(String args[]){
		Servidor c = new Servidor();
	}

}