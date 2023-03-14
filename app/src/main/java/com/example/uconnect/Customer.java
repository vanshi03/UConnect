package com.example.uconnect;

public class Customer {
    private String fname;
    private String lname;
    private String phone;
    private String address;
    private String latitude;
    private String longitude;
    public Customer(){

    }
    public Customer(String fname, String lname, String phone, String address, String latitude, String longitude) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.address = address;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {return latitude;}

    public void setLatitude(String latitude) {this.latitude = latitude;}

    public String getLongitude() {return longitude;}

    public void setLongitude(String longitude) {this.longitude = longitude;}
}
