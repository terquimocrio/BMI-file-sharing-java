/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.distributed_systems.middleware;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author diego
 */
public class Config {
    static final int SERVER_LISTENING_PORT = 3000;
    static final InetAddress SERVER_ADDRESS;
    private static final String HOST_NAME = "localhost";

    static {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(HOST_NAME);
        } catch (UnknownHostException e) {
            // Handle the exception, e.g., log it or set a default value
            System.err.println("Error initializing SERVER_ADDRESS: " + e.getMessage());
            // Set a default value or rethrow as an unchecked exception
            address = InetAddress.getLoopbackAddress();
        }
        SERVER_ADDRESS = address;
    }
}
