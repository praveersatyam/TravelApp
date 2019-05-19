package com.travel.util;

import com.travel.entity.City;
import com.travel.entity.Tour;

import java.util.List;

public class ConvertUtil {
    public static String getPathFromCityIdList(List<Integer> cityIdList, Tour tour){
        String pathString="";
        for (int i = 0; i < cityIdList.size(); i++) {
            String tempCityName = getCityNameByCityId(cityIdList.get(i), tour);
            if(i==cityIdList.size()-1){
                pathString+=tempCityName;
            }
            else{
                pathString+=tempCityName+"->";
            }
        }
        return pathString;
    }

    public static City getCityByCityName(String destinationName, Tour tour) {
        for (City city : tour.getCityList()) {
            if (city.getName().equals(destinationName))
                return city;
        }
        return null;
    }

    public static String getCityNameByCityId(Integer destinationId, Tour tour) {
        for (City city : tour.getCityList()) {
            if (city.getId().equals(destinationId))
                return city.getName();
        }
        return null;
    }

}
