package com.laurencebohannan.interveiw.udp.listener;

import com.laurencebohannan.interveiw.udp.model.Location;
import com.laurencebohannan.interveiw.udp.model.SequenceComparable;

import java.util.*;

/**
 * Created by bohannan on 6/3/15.
 */
public class LocationAggregator {

    private static Map<String,Map<Integer,Location>> devicePositionHistoryMap = Collections.synchronizedMap(new HashMap<String, Map<Integer,Location>>());

    public static boolean contains(Location location) {
        int index = location.getSequenceNumber() - 1;
        if (!devicePositionHistoryMap.isEmpty() && devicePositionHistoryMap.containsKey(location.getUuid()) &&
                devicePositionHistoryMap.get(location.getUuid()).containsKey(index)) {
            return true;
        }
        return false;
    }

    public static void addToMap(Location location) {
        int index = location.getSequenceNumber()-1;
        String key = location.getUuid();
        synchronized (devicePositionHistoryMap) {
            if (devicePositionHistoryMap.containsKey(key) &&
                    !devicePositionHistoryMap.get(key).containsKey(index)) {
                devicePositionHistoryMap.get(key).put(index,location);
            } else {
                Map<Integer, Location> locationsList = new HashMap<Integer, Location>();
                locationsList.put(index, location);
                devicePositionHistoryMap.put(key, locationsList);
            }
        }
//        System.out.println("ArraySize: "+devicePositionHistoryMap.get(key).size());
    }

    public static List<Location> getLocationsByUuid(String key){
        if(devicePositionHistoryMap.containsKey(key)){
            Map<Integer,Location> locationMap = devicePositionHistoryMap.get(key);
            List<Location> ary = new ArrayList<Location>();
            ary.addAll(locationMap.values());
            Collections.sort(ary, new SequenceComparable());
            return ary;
        }
        return null;
    }

    public static Set<String> getDeviceKeys(){
        return devicePositionHistoryMap.keySet();
    }
}
