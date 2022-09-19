package com.example.yatra;

public class Tickets {
    String p_name,p_from,p_to,p_fare;

    public Tickets(String p_name, String p_from, String p_to, String p_fare) {

        this.p_name = p_name;
        this.p_from = p_from;
        this.p_to = p_to;
        this.p_fare = p_fare;
    }
    public Tickets(){

    }

    public String getP_name() {
        return p_name;
    }

    public String getP_from() {
        return p_from;
    }

    public String getP_to() {
        return p_to;
    }

    public String getP_fare() {
        return p_fare;
    }
}
