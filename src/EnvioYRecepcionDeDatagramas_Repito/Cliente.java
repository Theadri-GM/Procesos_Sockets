package EnvioYRecepcionDeDatagramas_Repito;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        // Declaramos el puerto de nuestro servidor.
        final int puerto_servidor = 50000;
        // Y nuestro buffer donde guardaremos los mensajes que nos enviemos entre nosotros.
        byte[] buffer = new byte[1024];

        try{
                // Instanciamos la ip de nuestro servidor que en este caso se encuentra en localhost.
                InetAddress ipServidor = InetAddress.getByName("localhost");
                // Creamos nuestro DatagramSocket que es el que va a mandar nuestro mensaje.
                DatagramSocket socketUDP = new DatagramSocket();
                // Pasamos el mensaje que queremos que le envie a nuestro servidor.
                System.out.println("Mensaje a enviar: ");
                String mensaje = new Scanner(System.in).nextLine();
                buffer = mensaje.getBytes();
                // Ahora, creamos un DatagramPacket con el que crearemos la pregunta que le queremos mandar al server.
                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, ipServidor, puerto_servidor);
                // Y le mandamos el mensaje al server.
                System.out.println("Enviando el mensaje al servidor...");
                socketUDP.send(pregunta);

                // Ahora, crearemos un DatagramPacker que almacenará la respuesta del servidor.
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                // Recibimos los paquetes de nuestro servidor.
                socketUDP.receive(peticion);
                System.out.println("Acabo de recibir la informacion.");
                // Almacenamos el mensaje que nos ha mandado nuestro servidor en la variable mensaje.
                mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                // Y cerramos nuestro socket.
                socketUDP.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
