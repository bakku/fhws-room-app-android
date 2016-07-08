package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.sorter.UsedRoomsComparator;
import de.fhws.fiw.mobile.applications.roommodule.viewholder.UsedRoomsViewHolder;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

public class UsedRoomsAdapter extends RecyclerView.Adapter<UsedRoomsViewHolder> {

    private int layout;
    private List<Room> usedRooms;

    public UsedRoomsAdapter(int layout) {
        this.layout = layout;
        this.usedRooms = new LinkedList<>();
        sortByFreeInMinutes();
    }

    public UsedRoomsAdapter(List<Room> usedRooms, int layout){
        this.layout = layout;
        this.usedRooms = usedRooms;
    }

    public void clear() {
        this.usedRooms = new LinkedList<>();
        notifyDataSetChanged();
    }

    public void addAllUsedRooms(List<Room> usedRooms) {
        this.usedRooms.addAll(usedRooms);
        sortByFreeInMinutes();
        notifyDataSetChanged();
    }

    @Override
    public UsedRoomsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new UsedRoomsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsedRoomsViewHolder holder, int position) {
        holder.assignData(this.usedRooms.get(position));
    }

    @Override
    public int getItemCount() {
        return this.usedRooms.size();
    }

    public void addUsedRoom(Room usedRoom){
        this.usedRooms.add(usedRoom);
        sortByFreeInMinutes();
        notifyItemInserted(determinePosition(usedRoom));
    }

    private void informAdapterAboutChange() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    private void sortByFreeInMinutes(){
        Collections.sort(this.usedRooms, new UsedRoomsComparator());
    }

    private int determinePosition(Room room) {
        int newPosition = 0;

        for (int i = 0; i < getItemCount(); i++) {
            if (this.usedRooms.get(i).getRoomName().equals(room.getRoomName())) {
                newPosition = i;
                break;
            }

        }

        return newPosition;
    }
}