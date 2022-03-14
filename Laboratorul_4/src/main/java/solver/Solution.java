package solver;

import model.Street;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Street> streets = new ArrayList<>();

    public Solution() {

    }

    public Solution(List<Street> streets) {
        this.streets = streets;
    }

    public void setStreet(Street street) {
        this.streets.add(street);
    }
    
    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<Street> getStreets() {
        return streets;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "streets=" + streets +
                '}';
    }
}
