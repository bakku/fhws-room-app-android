package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.SearchAdapter;

/**
 * Created by christian on 06/07/16.
 */
public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final RecyclerView searchRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_search, container, false);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(searchRecyclerView.getContext()));

        searchRecyclerView.setAdapter(new SearchAdapter());

        return searchRecyclerView;
    }
}
