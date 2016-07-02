package de.fhws.fiw.mobile.applications.roommodule.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Kim on 10.06.2016.
 */
public class UsedRoomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView nameOfRoom;
    private final TextView freeInValue;
    private final TextView freeForValue;

    private final Context context;

    public UsedRoomsViewHolder(View itemView) {
        super(itemView);
        this.nameOfRoom = (TextView)itemView.findViewById(R.id.room_entry_room_name);
        this.freeInValue = (TextView)itemView.findViewById(R.id.room_entry_free_in_value);
        this.freeForValue = (TextView)itemView.findViewById(R.id.room_entry_free_for_value);

        this.context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void assignData(Room room){

        this.nameOfRoom.setText(room.getRoomName());
        this.freeForValue.setText(room.getFreeForMinutes());
        this.freeInValue.setText(room.getFreeInMinutes());

    }

    @Override
    public void onClick(View v) {

    }
}
