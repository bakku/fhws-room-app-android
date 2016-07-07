package de.fhws.fiw.mobile.applications.roommodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.viewholder.DetailInformationViewHolder;

/**
 * Created by student on 16.06.16.
 */

public class DetailInformationAdapter extends RecyclerView.Adapter<DetailInformationViewHolder> {

    private int layout;
    private List<String> roomInfo;

    public DetailInformationAdapter(int layout) {
        this.layout = layout;
        this.roomInfo = new LinkedList<>();
        fillWithData();
    }

    @Override
    public DetailInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new DetailInformationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailInformationViewHolder holder, int position) {
        holder.assignData(this.roomInfo.get(position));
    }

    @Override
    public int getItemCount() {
        return this.roomInfo.size();
    }

    private void fillWithData() {
        //TODO: add real data
        roomInfo.add("32 Plätze");
        roomInfo.add("2  Beamer");
        roomInfo.add("1  Whiteboard");
    }
}
