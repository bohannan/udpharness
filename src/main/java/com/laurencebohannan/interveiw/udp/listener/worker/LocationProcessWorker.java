package com.laurencebohannan.interveiw.udp.listener.worker;

import com.laurencebohannan.interveiw.udp.listener.LocationAggregator;
import com.laurencebohannan.interveiw.udp.model.Location;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by bohannan on 6/3/15.
 */
public class LocationProcessWorker implements Runnable {

    DatagramPacket packet = null;
    byte[] data = null;


    public LocationProcessWorker(byte[] buffer) {
        this.packet = packet;
        this.data = buffer;
    }

    @Override
    public void run() {
//        byte[] data = packet.getData();
//        System.out.println("data received: " + String.valueOf(data.length));


        // Convert the contents to a location, and displays it
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(in);

            Location location = (Location) is.readObject();

            if(!LocationAggregator.contains(location)){
                LocationAggregator.addToMap(location);
            }
//            System.out.println("Trans: " + location.toString());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        // Reset the length of the packet before reusing it.
//        packet.setLength(buffer.length);

    }
}
