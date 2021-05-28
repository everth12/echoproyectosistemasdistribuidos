/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import static java.util.Optional.empty;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author evert
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int PUERTO = 5001;
        byte[] buffer = new byte[1024];
       

        try {
            System.out.println("Iniciado el servidor UDP");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            //Siempre atendera peticiones
            while (true) {
               
               
                //Preparo la respuesta
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
              
             //  System.out.println("buffer.length= ");
               //System.out.println (peticion.getData().length);
               
                //Recibo el datagrama
                
                socketUDP.receive(peticion);
                
                System.out.println("Recibo la informacion del cliente");
               //String anadir=string.isEmpty();
                //Convierto lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData(),0,peticion.getLength());
                 if((mensaje.isBlank() || mensaje.isEmpty()))
                   socketUDP.close();
               //System.out.println (mensaje);
                String anadir = "Soy el servidor-".concat(mensaje);
                
                
                System.out.println(mensaje);
                mensaje=null;
                mensaje=anadir;
                
               

                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                System.out.println(puertoCliente);
                InetAddress direccion = peticion.getAddress();
                buffer=null;
                buffer=new byte[1024];
                //mensaje = "¡Hola mundo desde el servidor!";
                buffer = mensaje.getBytes();
                System.out.println(mensaje);
                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                //Envio la información
                System.out.println("Envio la informacion del cliente");
                socketUDP.send(respuesta);
               
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
        
        // TODO code application logic here
    }
    

