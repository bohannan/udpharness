package com.laurencebohannan.interveiw.udp.listener;

import com.laurencebohannan.interveiw.udp.listener.worker.LocationProcessWorker;
import com.laurencebohannan.interveiw.udp.model.Location;
import com.laurencebohannan.interveiw.udp.util.Utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bohannan on 6/2/15.
 */
public class UDPServer implements Runnable {

    private int PORT = 9000;
    private static final int BUFFER_SIZE = 2048;
    protected volatile boolean isStopped = false;
    protected DatagramSocket dsocket = null;
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public UDPServer(int PORT) {
        this.PORT = PORT;
        // Create a socket to listen on the PORT.
    }

    public void listen(){
        try {

            dsocket = new DatagramSocket(PORT);

            // Create a buffer to read bytes
            byte[] buffer = new byte[BUFFER_SIZE];

            // Create a packet to receive data into the buffer
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Now loop forever, waiting to receive packets and printing them.
            while (!isStopped) {
                // Wait to receive a datagram
                dsocket.receive(packet);
                byte[] data = packet.getData();

                executorService.execute(new LocationProcessWorker(data));

//                // Reset the length of the packet before reusing it.
                packet.setLength(buffer.length);
            }
        } catch (SocketException e) {
            // known socket Exception error till I find a more grace full method of disconnecting the blocking socket
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        this.listen();
    }

    public synchronized void stop(){
        System.out.println("Shutting down Listener");
        this.isStopped = true;
        executorService.shutdown();
        this.dsocket.close();
    }


    public void interrupt(){
        this.interrupt();
        this.dsocket.close();
    }
}
