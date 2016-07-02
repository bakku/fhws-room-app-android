package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.viewholder.UsedRoomsViewHolder;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Kim on 10.06.2016.
 */
public class UsedRoomsAdapter extends RecyclerView.Adapter<UsedRoomsViewHolder> {

    private int layout;
    private List<Room> usedRooms;

    public UsedRoomsAdapter(int layout) {
        this.usedRooms = new LinkedList<>();
        this.layout = layout;
    }

    public UsedRoomsAdapter(List<Room> usedRooms, int layout){
        this(layout);
        this.usedRooms = usedRooms;
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

    private void sortByFreeInMinutes(){

    }
}