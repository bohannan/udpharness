package com.laurencebohannan.interveiw.udp.client;

import com.laurencebohannan.interveiw.udp.client.UDPClient;
import com.laurencebohannan.interveiw.udp.model.Location;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by bohannan on 6/2/15.
 */
public class Harness implements Runnable {

    private String HOST = "localhost";
    private int PORT = 9000;
    public static final int MINIMUM_DELAY = 2;  //2 milliseconds
    private static final int MESSAGE_SET_SIZE = 3;
    private UDPClient client;
    private List<Location> syncList;
    private List<Location> enumeratedList;
    private boolean allQuiet = false;
    private long snap = 0;

    public Harness(String host, int port) {
        this.HOST = host;
        this.PORT = port;
        client = new UDPClient(HOST, PORT);
        syncList = Collections.synchronizedList(new LinkedList<Location>());
        enumeratedList = new LinkedList<Location>();
    }

    public synchronized void enqueueLocation(Location location){

        syncList.add(location);
    }

    private void sendNextLocation() {
        if(!enumeratedList.isEmpty()) {
            client.send(enumeratedList.remove(0));

            snap = System.currentTimeMillis();
            allQuiet = false;
        }
    }

    private void checkForNewLocations(){
        if(!syncList.isEmpty()){
            Location location = syncList.remove(0);
            for(int i = 0 ; i < MESSAGE_SET_SIZE; i++ ){
                enumeratedList.add(location);
            }
        }
    }

    @Override
    public void run() {
        snap = System.currentTimeMillis();

        while(!allQuiet || (!syncList.isEmpty() && !enumeratedList.isEmpty())){


            checkForNewLocations();
            if(snap-System.currentTimeMillis()<2){
                try {
                    Thread.sleep(MINIMUM_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sendNextLocation();
//            System.out.println("synced list: "+syncList.size()+ " enum list: "+enumeratedList.size());
            if(System.currentTimeMillis()-snap>1000){
                allQuiet = true;
            }
        }
        System.out.println("Harness shutdown");
    }
}
