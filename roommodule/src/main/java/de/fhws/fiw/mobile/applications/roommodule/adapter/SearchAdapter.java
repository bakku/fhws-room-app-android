package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.viewholder.SearchViewHolder;

/**
 * Created by christian on 06/07/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Room> roomList;

    public SearchAdapter() {
        roomList = RoomData.getInstance().getAllRooms();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
