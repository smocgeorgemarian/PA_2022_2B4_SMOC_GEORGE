package solver;

import model.Intersection;
import model.Street;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Solution {
    private List<Street> streets = new ArrayList<>();
    Map<Intersection, List<Street>> cityMap = new HashMap<>();

    public Solution() {

    }

    public Solution(List<Street> streets) {
        this.streets = streets;
    }

    public void setStreet(Street street) {
        this.streets.add(street);
    }

    public void setNodeStreet(Intersection intersection, Street street) {
        if (cityMap.containsKey(intersection))
            cityMap.get(intersection).add(street);
        else {
            cityMap.put(intersection, new ArrayList<>());
            cityMap.get(intersection).add(street);
        }
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public Map<Intersection, List<Street>> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<Intersection, List<Street>> cityMap) {
        this.cityMap = cityMap;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "streets=" + streets +
                '}';
    }
}
