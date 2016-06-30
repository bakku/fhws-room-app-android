package de.fhws.fiw.mobile.applications.roommodule.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.DetailInformationAdapter;

/**
 * Created by student on 16.06.16.
 */

public class DetailInformationFragment extends Fragment {

    public DetailInformationFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.room_detail_list, parent, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.detailRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        RecyclerView.Adapter adapter = new DetailInformationAdapter();
        recyclerView.setAdapter(adapter);

        return rootView;
        }

    /*
    public interface OnSomethingClicked { // Has to be implemented in Activity
        void onClick(int id);
    }
    */
}
