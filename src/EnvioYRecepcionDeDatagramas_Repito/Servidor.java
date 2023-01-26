package EnvioYRecepcionDeDatagramas_Repito;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        // Declaramos el puerto de nuestro servidor.
        final int puerto_servidor = 50000;
        // Y nuestro buffer donde almacenaremos nuestros mensajes y respuestas.
        byte[] buffer = new byte[1024];

        try{
            System.out.println("Iniciamos el servidor UDP...");
            // Configuramos el socket con la entrada de nuestro cliente.
            DatagramSocket socketUDP = new DatagramSocket(puerto_servidor);
            // Ahora, lo ejecutamos continuamente.
            while(true){
                // Creamos el DatagramPacket y lo guardamos en la variable peticion.
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                // Ahora, nuestro DatagramSocket recibirá la petición del cliente.
                socketUDP.receive(peticion);
                System.out.println("Acabo de recibir la siguiente información del cliente:");
                // Guardamos el mensaje en un String.
                String mensajeRecibido = new String(peticion.getData());
                System.out.println((mensajeRecibido));
                // Guardamos el puerto del cliente.
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                // Y respondemos desde el servidor.
                System.out.println("Mensaje a enviar: ");
                String mensaje = new Scanner(System.in).nextLine();
                buffer = mensaje.getBytes();
                // Y respondemos gracias al DatagramPacket que vamos a crear a continuación.
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                // Y le enviamos la información al cliente.
                System.out.println("Envío el mensaje al cliente...");
                socketUDP.send(respuesta);
                System.out.println("Enviado.");
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
