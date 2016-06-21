package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.viewholder.AvailableRoomsViewholder;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Kim on 10.06.2016.
 */
public class AvailableRoomsAdapter extends RecyclerView.Adapter<AvailableRoomsViewholder> {

    private int layout;
    List<Room> availableRooms;

    public AvailableRoomsAdapter(List<Room> roomList,int layout) {
        //TODO
        this.layout = layout;
    }

    @Override
    public AvailableRoomsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new AvailableRoomsViewholder(v);
    }

    @Override
    public void onBindViewHolder(AvailableRoomsViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
