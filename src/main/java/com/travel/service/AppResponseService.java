package com.travel.service;

public class AppResponseService {
    public static String getResponseIfDestinationNotPresent(String fromDestination, String toDestination, Boolean isFromDestinationPresent) {
        if(!isFromDestinationPresent){
            return "No city named \""+fromDestination+"\" in database";
        }
        else{
            return "No city named \""+toDestination+"\" in database";
        }
    }
}
