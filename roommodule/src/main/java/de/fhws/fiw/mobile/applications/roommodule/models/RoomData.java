package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by christian on 09/06/16.
 */
public class RoomData {

    private static RoomData instance;

    public static RoomData getInstance() {
        if (instance == null) {
            instance = new RoomData();
        }

        return instance;
    }

    private List<Room> listOfRooms;

    public RoomData() {
        listOfRooms = new LinkedList<>();
    }

    public void addRoom(Room room) {
        listOfRooms.add(room);
    }

    public Room getRoom(int position) {
        return listOfRooms.get(position);
    }

    public Room getRoomByName(String roomName) {
        for (Room room : listOfRooms) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }

        return null;
    }


}
