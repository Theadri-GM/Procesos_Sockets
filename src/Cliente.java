/*

            El cliente deberá mandarle un saludo del ripo "Hola, soy XXX y quiero saludarle".
            Y el servidor deberá contestarle con otro de la forma. "Muchas gracias, yo también le saludo a usted".

            Para probarlos, deberéis abrir dos terminales ejecutando primero el servidor y después el cliente en la otra
            terminal. Deberéis mandar capturas de pantalla de cómo queda.

 */

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {

        final int puerto_servidor = 50000;
        byte[] buffer = new byte[1024];

        try{
            InetAddress ipServidor = InetAddress.getByName("localhost");

            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "Hola, soy Adrián y quiero saludarle";

            buffer = mensaje.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length ,ipServidor, puerto_servidor);

            System.out.println("Envio el datagrama.");
            socketUDP.send(pregunta);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion);
            System.out.println("Recibo la petición.");

            mensaje = new String (peticion.getData());
            System.out.println(mensaje);

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
