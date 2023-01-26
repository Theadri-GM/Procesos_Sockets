package EnvíoRecepcionDeDatagrama;/*

            El cliente deberá mandarle un saludo del ripo "Hola, soy XXX y quiero saludarle".
            Y el servidor deberá contestarle con otro de la forma. "Muchas gracias, yo también le saludo a usted".

            Para probarlos, deberéis abrir dos terminales ejecutando primero el servidor y después el cliente en la otra
            terminal. Deberéis mandar capturas de pantalla de cómo queda.

 */

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Instanciamos el puerto en el que se encuentra el servidor.
        final int puerto_servidor = 50000;
        // y el buffer que vamos a usar para almacenar las respuestas.
        byte[] buffer = new byte[1024];
        // Bloque try-catch que...
        try{
            // Instanciamos la ip de nuestro servidor que en este caso será localhost.
            InetAddress ipServidor = InetAddress.getByName("localhost");
            // Ahora, creamos nuestro DatagramSocket que es el que va a mandar nuestro mensaje preguntandole al server.
            DatagramSocket socketUDP = new DatagramSocket();
            // Ahora, almacenamos nuestro mensaje en una variable.
            String mensaje = "Hola, soy Adrián y quiero saludarle";
            // Y ese mensaje lo almacenamos en el buffer que hemos creado antes.
            buffer = mensaje.getBytes();
            // Ahora, creamos un DatagramPacker que le preguntará al server.
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length ,ipServidor, puerto_servidor);
            // Y mandamos la pregunta.
            System.out.println("Envio el datagrama.");
            socketUDP.send(pregunta);

            // Ahora, crearemos un DatagramPacket que almacenará la respuesta de nuestro server.
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            // Recibimos los paquetes de nuestro servidor.
            socketUDP.receive(peticion);
            System.out.println("Recibo la petición.");
            // Almacenamos en la variable mensaje los datos de nuestra petición de datos al server.
            mensaje = new String (peticion.getData());
            // Mostramos los mensajes.
            System.out.println(mensaje);
            // Y cerramos nuestro socketUDP para que no se encuentré abierto todo el rato.
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
