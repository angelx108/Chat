import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.DataOutputStream;

public class Cliente extends JFrame{

	private JPanel panel;
	private TextArea texto;
	private JButton boton;

	public Cliente(){
		//metodos de la ventana
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("Cliente");
		setLocationRelativeTo(null);

		//instanciar los elementos de la ventana
		panel = new JPanel();
		texto = new TextArea();
		boton = new JButton("Enviar");

		//personalizar los elementos de la ventana
		panel.setSize(500,500);
		panel.setBackground(Color.gray);
		texto.setColumns(100);

		//se crea la accion realizada por el boton
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					//se crea el socket con la IP y el puerto
					Socket socket = new Socket("192.168.1.66",8888);

					//se crea un stream (flujo) de salida que circulara por el socket
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
					
					//se captura el texto del textArea y se envia atraves del flujo creado
					String mensaje = "";
					mensaje = texto.getText();
					salida.writeUTF(mensaje);

					//se cierra el flujo de salida
					salida.close();

				}catch(UnknownHostException e1){
					JOptionPane.showMessageDialog(null,e1.toString());
				}catch(IOException e2){
					JOptionPane.showMessageDialog(null,e2.toString());
				}

			}
		});

		//añadirlos a la ventana
		panel.add(texto);
		panel.add(boton);
		this.add(panel);

	}

	public static void main(String args[]){
		Cliente c = new Cliente();
	}

}