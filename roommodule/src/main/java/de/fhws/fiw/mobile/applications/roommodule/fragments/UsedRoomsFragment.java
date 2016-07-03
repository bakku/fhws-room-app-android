package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.UsedRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;

public class UsedRoomsFragment extends Fragment{

    private UsedRoomsAdapter usedRoomsAdapter;

    public UsedRoomsFragment(){
        this.usedRoomsAdapter = new UsedRoomsAdapter(RoomData.getInstance().getUsedRooms(), R.layout.room_list_entry);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.room_entries_recycler_view, container, false);

        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        rv.setAdapter(this.usedRoomsAdapter);

        return rv;
    }

    public UsedRoomsAdapter getUsedRoomsAdapter() {
        return usedRoomsAdapter;
    }
}