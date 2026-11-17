package UDP_peer_to_peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import javax.xml.stream.events.StartDocument;

public class cliente {

	public static void main(String[] args) {

		start();
	}

	public static void start() {
		try {
			DatagramSocket sock = new DatagramSocket(8002, InetAddress.getByName("130.10.1.54"));

			
			//envio de numero
			byte[] dN = ByteBuffer.allocate(4).putInt( 5 ).array();
			DatagramPacket datoNum = new DatagramPacket(
					dN, 
					dN.length, 
					InetAddress.getByName("localhost"), 
					8001);

			//envio de string
			DatagramPacket datoString = new DatagramPacket(
					("").getBytes(),
					("").getBytes().length,
					InetAddress.getByName("localhost"),
					8001);
			
			//enviar datos compactados como Class
			MisDatos obj = new MisDatos("pep", 45788965);
			byte[] datos = obj.toByteArray();
			
			DatagramPacket datoObjeto = new DatagramPacket(
					datos,   							//1.Que envias (En bytes)
					datos.length , 						//2. Cuanto pesa
					InetAddress.getByName("localhost"),//3. A que IP va
					8001);								//4.A que puerto va ( si o si debe coincidir con el servidor)
			
			sock.send(datoNum);		//Disparar el paquete 
			Thread.sleep(1000);
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
