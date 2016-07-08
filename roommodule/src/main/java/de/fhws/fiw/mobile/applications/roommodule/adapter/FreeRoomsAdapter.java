package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.sorter.FreeRoomsComparator;
import de.fhws.fiw.mobile.applications.roommodule.viewholder.FreeRoomsViewHolder;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class FreeRoomsAdapter extends RecyclerView.Adapter<FreeRoomsViewHolder> {

    private int layout;
    private List<Room> freeRooms;

    public FreeRoomsAdapter(int layout) {
        this.layout = layout;
        this.freeRooms = new LinkedList<>();
    }

    public FreeRoomsAdapter(List<Room> freeRooms, int layout) {
        this.layout = layout;
        this.freeRooms = freeRooms;
        sortByFreeForMinutes();
    }

    public void clear() {
        this.freeRooms = new LinkedList<>();
        notifyDataSetChanged();
    }

    public void addAllFreeRooms(List<Room> freeRooms) {
        this.freeRooms.addAll(freeRooms);
        sortByFreeForMinutes();
        notifyDataSetChanged();
    }

    @Override
    public FreeRoomsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new FreeRoomsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FreeRoomsViewHolder holder, int position) {
        holder.assignData(this.freeRooms.get(position));
    }

    @Override
    public int getItemCount() {
        return this.freeRooms.size();
    }

    public void addFreeRoom(Room freeRoom) {
        this.freeRooms.add(freeRoom);
        sortByFreeForMinutes();
        notifyItemInserted(determinePosition(freeRoom));
    }

    private void informAdapterAboutChange() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    private void sortByFreeForMinutes() {
        Collections.sort(this.freeRooms, new FreeRoomsComparator());
    }

    private int determinePosition(Room room) {
        int newPosition = 0;

        for (int i = 0; i < getItemCount(); i++) {
            if (this.freeRooms.get(i).getRoomName().equals(room.getRoomName())) {
                newPosition = i;
                break;
            }

        }

        return newPosition;
    }
}