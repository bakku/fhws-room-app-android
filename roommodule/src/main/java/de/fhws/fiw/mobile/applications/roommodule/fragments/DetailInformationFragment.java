package de.fhws.fiw.mobile.applications.roommodule.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhws.fiw.mobile.applications.roommodule.R;

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
        //Initialize a view/layout to work with (similar to setContentView)
        final View rootView = inflater.inflate(R.layout.room_detail_list, parent, false);
        //Do some fancy stuff, for example
        //final Button button = (Button)rootView.findViewById(R.id.button);

        return rootView;
        }

    /*
    public interface OnSomethingClicked { // Has to be implemented in Activity
        void onClick(int id);
    }
    */
}
