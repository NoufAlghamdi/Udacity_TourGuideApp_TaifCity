package com.example.android.taifcity.models;

public class Hotel {
    private int name;
    private int photograph;
    private int hotelClass;
    private int distanceFromCityCenter;
    private int website;
    private int phoneNumber;
    private int location;

    public Hotel(int name, int photograph, int hotelClass, int distanceFromCityCenter, int website,
                 int phoneNumber, int location) {
        this.name = name;
        this.photograph = photograph;
        this.hotelClass = hotelClass;
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public int getName() {
        return name;
    }

    public int getPhotograph() {
        return photograph;
    }

    public int getHotelClass() {
        return hotelClass;
    }

    public int getDistanceFromCityCenter() {
        return distanceFromCityCenter;
    }

    public int getWebsite() {
        return website;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getLocation() {
        return location;
    }
}
