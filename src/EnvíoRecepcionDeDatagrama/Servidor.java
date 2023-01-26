package EnvíoRecepcionDeDatagrama;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {

    public static void main(String[] args) {
        // Declaramos el puerto del servidor.
        final int puerto_servidor = 50000;
        // y un buffer donde almacenaremos mensajes y respuestas.
        byte[] buffer = new byte[1024];
        //try-catch...
        try{
            System.out.println("Iniciando el servidor UDP");
            // Configuramos el socket de nuestro servidor.
            DatagramSocket socketUDP = new DatagramSocket(puerto_servidor);
            // Se va a estar ejecutando continuamente.
            while(true){
                // Creamos un DatagramPacket y lo guardamos en la variable peticion.
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                // Ahora, nuestro DatagramSocket recibirá la petición del cliente.
                socketUDP.receive(peticion);
                System.out.println("Recibo la información del cliente.");
                // Guardamos el mensaje en un String el resultado de nuestra petición.
                String mensaje = new String(peticion.getData());
                System.out.println((mensaje));
                // Guardamos en un entero el puerto del cliente.
                int puertoCliente = peticion.getPort();
                // y guardamos la direccion de nuestro cliente en un InetAdress.
                InetAddress direccion = peticion.getAddress();
                // Y nos responde el servidor...
                mensaje = "Muchas gracias, yo también le saludo a usted.";
                buffer = mensaje.getBytes();
                // mediante el DatagramPacket.
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                // Y le enviamos la información al cliente.
                System.out.println("Envío la información del cliente");
                socketUDP.send(respuesta);
                // Y paramos el programa para que no se siga ejecutando continuamente.
                break;
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
