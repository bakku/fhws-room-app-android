package de.fhws.fiw.mobile.applications.roommodule.sorter;

import java.util.Comparator;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 03.07.2016.
 */
public class FreeRoomsComparator implements Comparator<Room> {

    public int compare(Room roomOne, Room roomTwo){

        int result;

        if(roomOne.calculateFreeForMinutes() < roomTwo.calculateFreeForMinutes()){
            result = 1;
        } else if(roomOne.calculateFreeForMinutes() > roomTwo.calculateFreeForMinutes()){
            result = -1;
        } else{
            result = 0;
        }

        return result;
    }
}