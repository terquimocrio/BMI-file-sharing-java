/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.distributed_systems.server;

import com.distributed_systems.models.Person;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author diego
 */
public class Server {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Server listening at port " + Config.LISTENING_PORT);
        try {
            // TODO code application logic here
            DatagramSocket socket = new DatagramSocket(Config.LISTENING_PORT);
            
            byte[] dataToSendToClient = new byte[Config.MAX_PACKET_SIZE];
            byte[] receivedDataFromClient = new byte[Config.MAX_PACKET_SIZE];
            
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(receivedDataFromClient, Config.MAX_PACKET_SIZE);
                socket.receive(receivePacket);
                
                String personXML = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                System.out.println("String recibido" + personXML);
                
                Person person = Utils.parsePersonXML(personXML);
                person.setBMI();
                
                File returnedFile = Utils.getResultFile(person);
                
                // Envía la respuesta al cliente
                byte[] returnedFileBytes = Files.readAllBytes(returnedFile.toPath());
                DatagramPacket sendPacket = new DatagramPacket(returnedFileBytes, returnedFileBytes.length, receivePacket.getAddress(), 3001);
                socket.send(sendPacket);
                
                
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (JAXBException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
