/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.distributed_systems.middleware;

import com.distributed_systems.server.Server;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author diego
 */
public class Middleware {
    private Server server;
    public Middleware(){
        this.server = new Server();
    }
    
    public void sendFile(File file){
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            DatagramPacket packet = new DatagramPacket(fileBytes, fileBytes.length, Config.SERVER_ADDRESS, Config.SERVER_LISTENING_PORT);
            socket.send(packet);
            
        } catch (IOException ex) {
            Logger.getLogger(Middleware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveFile(){
        System.out.println("Waiting from server response");
        
        boolean stop = false;
        
        try {
            // TODO code application logic here
            DatagramSocket socket = new DatagramSocket(3001);
            
            byte[] dataToSendToClient = new byte[com.distributed_systems.server.Config.MAX_PACKET_SIZE];
            byte[] receivedDataFromServer = new byte[com.distributed_systems.server.Config.MAX_PACKET_SIZE];
            
            while(!stop){
                DatagramPacket receivePacket = new DatagramPacket(receivedDataFromServer, com.distributed_systems.server.Config.MAX_PACKET_SIZE);
                socket.receive(receivePacket);
                
                String personXML = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                System.out.println("RECEIVED PERSON XML" + personXML);
                
                FileWriter fileWriter = new FileWriter("results_from_server.xml");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
                bufferedWriter.write(personXML);
                bufferedWriter.close();
                fileWriter.close();
                
                
                stop = true;

                
         
                
                
 
            }
            File xmlFile = new File("results_from_server.xml");
            return xmlFile.getAbsolutePath();
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    
    
}
