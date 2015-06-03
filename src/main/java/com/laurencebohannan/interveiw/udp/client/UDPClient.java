package com.laurencebohannan.interveiw.udp.client;

import com.laurencebohannan.interveiw.udp.model.Location;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by bohannan on 6/2/15.
 */
public class UDPClient {

    private String HOST = "localhost";
    private int PORT = 9000;
    private static final int BUFFER_SIZE = 2048;


    public UDPClient(String hostname, int portNumber) {
        this.HOST = hostname;
        this.PORT = portNumber;
    }

    public void send(Location location) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(location);
            oos.close();

            byte[] msgBytes = baos.toByteArray();
            baos.close();

            // Get the internet address of the specified HOST
            InetAddress address = InetAddress.getByName(HOST);

            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(msgBytes, msgBytes.length,
                    address, PORT);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
