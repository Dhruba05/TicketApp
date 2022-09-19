package com.example.yatra;

public class bookedTickets {
    String bh_name,bh_source,bh_destination,bh_baseprice,bh_passengerNumber,bh_amount;

    public bookedTickets(String bh_name, String bh_source, String bh_destination, String bh_baseprice, String bh_passengerNumber, String bh_amount) {
        this.bh_name = bh_name;
        this.bh_source = bh_source;
        this.bh_destination = bh_destination;
        this.bh_baseprice = bh_baseprice;
        this.bh_passengerNumber = bh_passengerNumber;
        this.bh_amount = bh_amount;
    }

    public bookedTickets(){

    }

    public String getBh_name() {
        return bh_name;
    }

    public String getBh_source() {
        return bh_source;
    }

    public String getBh_destination() {
        return bh_destination;
    }

    public String getBh_baseprice() {
        return bh_baseprice;
    }

    public String getBh_passengerNumber() {
        return bh_passengerNumber;
    }

    public String getBh_amount() {
        return bh_amount;
    }
}
