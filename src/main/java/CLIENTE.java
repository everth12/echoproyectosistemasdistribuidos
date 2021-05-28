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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author evert
 */
public class CLIENTE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         final int PUERTO_SERVIDOR = 5001;
        //buffer donde se almacenara los mensajes
        byte[] buffer = new byte[1024];

        try {
            //Obtengo la localizacion de localhost
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();
            
        System.out.println ("Por favor introduzca una cadena por teclado:");
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in);
        entradaTeclado = entradaEscaner.nextLine ();
        
        if(entradaTeclado.isBlank() || entradaTeclado.isEmpty())
        {   
            String hey="";
        buffer=new byte[1024];
        buffer=hey.getBytes();
        
        DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
            socketUDP.send(pregunta);
            socketUDP.close();
        }
        
        do{
        
        
        String mensaje=entradaTeclado;
            

            //se convierte el mensaje a byte
            buffer = mensaje.getBytes();
            
            //Creo un datagrama con sus parametros
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            //se envia al servidor con send
            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta);
            buffer=new byte[1024];
            
            //Preparo la respuesta
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            //Recibo la respuesta
            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");

            //Cojo los datos y lo muestro
            mensaje = new String(peticion.getData(), 0, peticion.getLength());
            System.out.println(mensaje);
            
        System.out.println ("Por favor introduzca una cadena por teclado:");
        entradaTeclado = entradaEscaner.nextLine ();
            }while(!"".equals(entradaTeclado));
        String hey="";
        buffer=new byte[1024];
        buffer=hey.getBytes();
        
        DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
            socketUDP.send(pregunta);
            //cierro el socket
            socketUDP.close();
            

        } catch (SocketException ex) {
            Logger.getLogger(CLIENTE.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (IOException ex) {
            Logger.getLogger(CLIENTE.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
