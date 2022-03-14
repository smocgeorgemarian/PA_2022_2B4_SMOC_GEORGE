package model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class City {
    private Map<Intersection, List<Street>> cityMap = new HashMap<>();

    public void setIntersectionData(Intersection intersection, List<Street> streetList) {
        cityMap.put(intersection, streetList);
    }

    public void setCityMap(Map<Intersection, List<Street>> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<Intersection, List<Street>> getCityMap() {
        return cityMap;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityMap=" + cityMap +
                '}';
    }
}
