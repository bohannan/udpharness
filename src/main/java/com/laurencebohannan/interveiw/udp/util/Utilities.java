package com.laurencebohannan.interveiw.udp.util;

import com.laurencebohannan.interveiw.udp.model.Location;

import java.util.List;
import java.util.Random;

/**
 * Created by bohannan on 6/3/15.
 */
public class Utilities {

    public static short getNewRandomMovement(int previousLocation) {
        Random numGen = new Random();
        int newNum = numGen.nextInt(3)-1;
        if (Math.abs(newNum - previousLocation) == 2)
            return 0;
        return (short) newNum;

    }

    public static String getCondensedReport(List<Location> referenceList, List<Location> locationList){
        StringBuffer sb = new StringBuffer();
        String uuid = null;
        String state = null;
        String prevState = null;
        String seqString = null;
        boolean first = true;

        sb.append("Harness location list contained ");
        sb.append(referenceList.size());
        sb.append(" messages sets while the server contained ");
        sb.append(locationList.size());
        sb.append(" messages sets.\n");
        sb.append((locationList.size()/(float)referenceList.size())*100);
        sb.append("% of messages received.\n\nBelow is a condensed list of the received message sets in order.\n\n");

        for(Location l:locationList){
            if(uuid==null){
                uuid = l.getUuid();
                sb.append("{ uuid: "+uuid+"\n");
            }
            state = l.getState();
            if(prevState!=null&& state.equals(prevState)){
                sb.append(","+String.valueOf(l.getSequenceNumber()));
            } else {
                if(!first) {
                    sb.append("]\n\t"+state+" = ["+String.valueOf(l.getSequenceNumber()));
                } else {
                    sb.append("\t"+state+" = ["+String.valueOf(l.getSequenceNumber()));
                    first = false;
                }

            }
            prevState = state;
        }
        sb.append("]\n}");
        return sb.toString();
    }

    public String getState(Location l){
        return l.getXCoordinate() + ":" +l.getYCoordinate();
    }

}
