package com.laurencebohannan.interveiw.udp.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

/**
 * Created by bohannan on 6/2/15.
 */
public class Location implements Serializable{
    String uuid;
    int sequenceNumber;
    short XCoordinate;
    short YCoordinate;

    public Location(String uuid, int sequenceNumber, short XCoordinate, short YCoordinate) {
        this.uuid = uuid;
        this.sequenceNumber = sequenceNumber;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public String getUuid() {
        return uuid;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public short getXCoordinate() {
        return XCoordinate;
    }

    public short getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (sequenceNumber != location.sequenceNumber) return false;
        if (!uuid.equals(location.uuid)) return false;

        return true;
    }

    public String getState(){
        return this.getXCoordinate() + ":" +this.getYCoordinate();
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + sequenceNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "uuid='" + uuid + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", XCoordinate=" + XCoordinate +
                ", YCoordinate=" + YCoordinate +
                '}';
    }


}
