package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.viewholder.SearchViewHolder;

/**
 * Created by christian on 06/07/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Room> roomList;

    private List<Room> filteredList;

    public SearchAdapter() {
        roomList = RoomData.getInstance().getAllRooms();
        filteredList = new LinkedList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_entry, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.assignData(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();

        for (Room room : roomList) {
            if (room.getRoomName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(room);
            }
        }

        notifyDataSetChanged();
    }
}
