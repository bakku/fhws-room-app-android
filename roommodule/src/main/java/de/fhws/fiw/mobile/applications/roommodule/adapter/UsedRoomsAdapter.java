package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.viewholder.UsedRoomsViewHolder;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Kim on 10.06.2016.
 */
public class UsedRoomsAdapter extends RecyclerView.Adapter<UsedRoomsViewHolder> {

    private int layout;
    private List<Room> usedRooms;

    public UsedRoomsAdapter(List<Room> rooms, int layout) {
        //TODO
        this.layout = layout;
    }

    @Override
    public UsedRoomsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new UsedRoomsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsedRoomsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
