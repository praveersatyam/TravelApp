package com.travel.entity;

public class Flight {
    private City fromDestination;
    private City toDestination;

    public City getFromDestination() {
        return fromDestination;
    }

    public void setFromDestination(City fromDestination) {
        this.fromDestination = fromDestination;
    }

    public City getToDestination() {
        return toDestination;
    }

    public void setToDestination(City toDestination) {
        this.toDestination = toDestination;
    }

}
