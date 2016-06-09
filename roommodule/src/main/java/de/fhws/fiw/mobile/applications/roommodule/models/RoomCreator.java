package de.fhws.fiw.mobile.applications.roommodule.models;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by christian on 09/06/16.
 */
public class RoomCreator {

    public static void createRoomsFromJsonArray(JSONArray array) {
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject roomObject = array.getJSONObject(i);

                Room room = new Room(roomObject.getString("label"));

                RoomData.getInstance().addRoom(room);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
