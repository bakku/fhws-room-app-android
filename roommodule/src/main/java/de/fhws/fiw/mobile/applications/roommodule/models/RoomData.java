package de.fhws.fiw.mobile.applications.roommodule.models;

import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.adapter.FreeRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.network.DownloadListener;
import de.fhws.fiw.mobile.applications.roommodule.network.RoomDownloader;

/**
 * Created by christian on 09/06/16.
 */
public class RoomData {

    private static RoomData instance;

    public static RoomData updateData(DownloadListener downloadListener, boolean refreshData) {
        return RoomData.getInstance(downloadListener, refreshData);
    }

    public static RoomData getInstance(DownloadListener downloadListener, boolean refreshData) {
        if (instance == null || refreshData) {
            instance = new RoomData(downloadListener);
        }

        return instance;
    }

    private final List<Room> listOfRooms;

    private RoomData(DownloadListener downloadListener) {
        listOfRooms = new LinkedList<>();
        downloadRooms(downloadListener);
    }

    private void downloadRooms(DownloadListener downloadListener){
        new RoomDownloader(downloadListener).execute();
        downloadListener.onDownloadStarted();
    }

    public List<Room> getAllRooms() {
        return listOfRooms;
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

    public List<Room> getFreeRooms(){
        List<Room> listOfFreeRooms = new LinkedList<>();

        for(Room room : this.listOfRooms){
            if(room.roomIsFree()){
                listOfFreeRooms.add(room);
            }
        }

        return listOfFreeRooms;
    }

    public List<Room> getUsedRooms(){
        List<Room> listOfUsedRooms = new LinkedList<>();

        for(Room room : this.listOfRooms){
            if(!room.roomIsFree()){
                listOfUsedRooms.add(room);
            }
        }

        return listOfUsedRooms;
    }
}