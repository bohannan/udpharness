package com.laurencebohannan.interveiw;

import com.laurencebohannan.interveiw.udp.client.Harness;
import com.laurencebohannan.interveiw.udp.listener.LocationAggregator;
import com.laurencebohannan.interveiw.udp.listener.UDPServer;
import com.laurencebohannan.interveiw.udp.model.Location;
import com.laurencebohannan.interveiw.udp.util.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class TestHarness
{

    public static void main( String[] args )
    {

        String uuid = "de305d54-75b4-431b-adb2-eb6b9e546014" ;
        int messageSetCount = 100;
        int duration = 5;
        int MILLI_SECONDS_PER_SECOND = 1000;
        int port = 9000;

        // initialized the location list with random movements
        List<Location> locationList = new ArrayList<Location>();
        Location loc = new Location(uuid,1,(short)0,(short)0);
        locationList.add(loc);
        for (int i =2 ; i <= messageSetCount; i++){
            locationList.add(new Location(uuid, i,
                    Utilities.getNewRandomMovement(loc.getXCoordinate()),
                    Utilities.getNewRandomMovement(loc.getYCoordinate())
                    )
            );
        }

        int messageDelay = duration * MILLI_SECONDS_PER_SECOND / messageSetCount;

        //start the Listener thread
        UDPServer server = new UDPServer(port);
        Thread serverThread = new Thread(server, "UdpListener");
        serverThread.start();

        System.out.println("Listener has been started");

        //start Harness thread
        Harness harness = new Harness("localhost",port);
        Thread harnessThread = new Thread(harness, "Harness");
        harnessThread.start();

        System.out.println("Harness has been started");


        try {

            // send message sets with delay in between
            for (Location location: locationList) {
                Thread.sleep(messageDelay);

                harness.enqueueLocation(location);

            }

            // wait for everything to finish sending
            harnessThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Notify the server to shutdown once done
        server.stop();

        Set<String> keys = LocationAggregator.getDeviceKeys();
        for(String key:keys){
            List<Location> ary = LocationAggregator.getLocationsByUuid(key);
//            for(Location l: ary){
//                System.out.println(l.toString());
//            }
            System.out.println();
            System.out.println(Utilities.getCondensedReport(locationList, ary));
        }
    }
}
