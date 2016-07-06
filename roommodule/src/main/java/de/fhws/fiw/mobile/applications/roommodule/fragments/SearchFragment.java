package de.fhws.fiw.mobile.applications.roommodule.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.SearchAdapter;

/**
 * Created by christian on 06/07/16.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchView searchView;

    private SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView searchRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_search, container, false);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(searchRecyclerView.getContext()));

        this.searchAdapter = new SearchAdapter();
        searchRecyclerView.setAdapter(this.searchAdapter);

        return searchRecyclerView;
    }

    public void setSearchView(SearchView view) {
        this.searchView = view;
        this.searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        boolean returnValue;

        if (isSearchAdapterInitialized()) {
            returnValue = false;
        }
        else {
            this.searchAdapter.filter(newText);
            returnValue = true;
        }

        return returnValue;
    }

    private boolean isSearchAdapterInitialized() {
        return this.searchAdapter == null;
    }
}
