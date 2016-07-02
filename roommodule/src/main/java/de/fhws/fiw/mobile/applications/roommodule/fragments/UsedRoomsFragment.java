package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.UsedRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.adapter.RoomAdapterTestData;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.network.DownloadListener;

public class UsedRoomsFragment extends Fragment implements DownloadListener{

    private UsedRoomsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.room_entries_recycler_view, container, false);

        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        this.adapter = new UsedRoomsAdapter(R.layout.room_list_entry);

        rv.setAdapter(this.adapter);

        return rv;
    }

    @Override
    public void onDownloadSuccess() {

        for(Room room : RoomData.getInstance().getAllRooms()){

            if(!room.roomIsFree()){
                this.adapter.addUsedRoom(room);
            }
        }
    }

    @Override
    public void onDownloadError() {
    }

    public UsedRoomsAdapter getAdapter() {
        return adapter;
    }
}