package com.antu.bazinga.antitheftdevice;

/**
 * Created by Antu on 10-May-18.
 */

public class DeviceInformation {
    private String time,longitude, latitude;

    public DeviceInformation(String time, String longitude, String latitude) {
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public DeviceInformation() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return longitude+" "+latitude;
    }
}
