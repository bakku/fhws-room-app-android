package de.fhws.fiw.mobile.applications.roommodule.network;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by christian on 09/06/16.
 */
public class BackendURLBuilder {

    private final String ALL_ROOMS_URL = "http://backend2.applab.fhws.de:8080/fhwsapi/v1/rooms";

    private final String ALL_LECTURES_URL = "http://backend2.applab.fhws.de:8080/fhwsapi/v1/lectures";

    private final String LECTURES_FOR_ROOM_AND_TIMESPAN = "http://backend2.applab.fhws.de:8080/fhwsapi/v1/lectures?room={ROOM}&from={START}&to={END}";

    public BackendURLBuilder() {}

    public BasicBackendURLBuilder allRooms() {
        return new BasicBackendURLBuilder(ALL_ROOMS_URL);
    }

    public BasicBackendURLBuilder allLectures() {
        return new BasicBackendURLBuilder(ALL_LECTURES_URL);
    }

    public LecturesForRoomURLBuilder1 lectures() {
        return new LecturesForRoomURLBuilder1(LECTURES_FOR_ROOM_AND_TIMESPAN);
    }

    public static class BasicBackendURLBuilder {
        private String currentUrl;

        public BasicBackendURLBuilder(String url) {
            this.currentUrl = url;
        }

        public String build() {
            return currentUrl;
        }
    }

    public class LecturesForRoomURLBuilder1 {
        private String currentUrl;

        public LecturesForRoomURLBuilder1(String url) {
            this.currentUrl = url;
        }

        public LecturesForRoomURLBuilder2 forRoom(Room room) {
            return new LecturesForRoomURLBuilder2(currentUrl.replace("{ROOM}", room.getRoomName()));
        }
    }

    public class LecturesForRoomURLBuilder2 {
        private String currentUrl;

        public LecturesForRoomURLBuilder2(String url) {
            this.currentUrl = url;
        }

        public LecturesForRoomURLBuilder3 andStartingDate(long start) {
            return new LecturesForRoomURLBuilder3(currentUrl.replace("{START}", start + ""));
        }
    }

    public class LecturesForRoomURLBuilder3 {
        private String currentUrl;

        public LecturesForRoomURLBuilder3(String url) {
            this.currentUrl = url;
        }

        public BasicBackendURLBuilder andEndDate(long end) {
            return new BasicBackendURLBuilder(currentUrl.replace("{END}", end + ""));
        }
    }

}
