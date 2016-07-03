package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
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

    public void addFreeRoom(Room freeRoom){
        this.freeRooms.add(freeRoom);
        informAdapterAboutChange();
    }

    private void informAdapterAboutChange() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    private void sortByFreeForMinutes(){

    }
}