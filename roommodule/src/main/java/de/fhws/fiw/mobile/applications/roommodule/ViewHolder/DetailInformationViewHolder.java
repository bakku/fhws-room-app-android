package de.fhws.fiw.mobile.applications.roommodule.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.fhws.fiw.mobile.applications.roommodule.R;

/**
 * Created by student on 16.06.16.
 */

public class DetailInformationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView tvInfo;

    public DetailInformationViewHolder(View itemView) {
        super(itemView);
        this.tvInfo = (TextView)itemView.findViewById(R.id.detailTextView);
    }

    @Override
    public void onClick(View v) {

    }

    public void assignData(String info) {
        this.tvInfo.setText(info);
    }
}
