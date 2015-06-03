package com.laurencebohannan.interveiw.udp.model;

import java.util.Comparator;

/**
 * Created by bohannan on 6/3/15.
 */
public class SequenceComparable implements Comparator<Location> {

    @Override
    public int compare(Location o1, Location o2) {
        int obj1 = o1.getSequenceNumber();
        int obj2 = o2.getSequenceNumber();
        return (obj2>obj1 ? -1 : (obj1 ==obj2 ? 0 : 1));
    }
}
