package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.views.RoomTestData;
import de.fhws.fiw.mobile.applications.roommodule.views.TimetableView;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 30.06.2016.
 */
public class TimetableFragment extends Fragment {

    private LinearLayout rootView;

    private Room room;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        this.rootView = (LinearLayout) inflater.inflate(R.layout.drawed_timetable, parent, false);

        loadRoomData();

        LinearLayout linearLayout = (LinearLayout) this.rootView.findViewById(R.id.scrollview_content);
        linearLayout.addView(new TimetableView(getContext(), null, this.room));

        return this.rootView;
    }

    private void loadRoomData() {
        this.room = new RoomTestData().getTestRoom();
    }
}