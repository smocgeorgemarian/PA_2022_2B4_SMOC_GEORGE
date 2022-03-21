package solver;

import model.City;
import model.Intersection;
import model.Street;

import java.util.*;

public class PrimAlgorithm implements Algorithm{
    private final City city;
    public PrimAlgorithm(City city) {
        this.city = city;
    }

    private int find(int node, int[] father) {
        int nodeCopy = node;
        while(father[node] != -1)
            node = father[node];

        int root = node;
        while(father[nodeCopy] != -1)
        {
            node = nodeCopy;
            nodeCopy = father[nodeCopy];
            father[node] = root;
        }
        return root;
    }

    private void union(int nodeX, int nodeY, int[] height, int[] father) {
        if (height[nodeX] > height[nodeY])
            father[nodeY] = nodeX;
        else if (height[nodeY] > height[nodeX])
                father[nodeX] = nodeY;
        else {
            height[nodeY]++;
            father[nodeX] = nodeY;
        }
    }

    @Override
    public Solution solve() {
        Set<Street> streetSet = new HashSet<>();
        Map<Intersection, List<Street>> cityMap = this.city.getCityMap();
        for (var pair: cityMap.entrySet())
            streetSet.addAll(pair.getValue());
        List<Street> streetList = new LinkedList<>(streetSet.stream().toList());
        List<Street> sortedStreetList = streetList.stream()
                .sorted(Comparator.comparing(Street::getLength)).toList();

        Map<Intersection, Integer> normalisation = new HashMap<>();
        int index = 0;
        for (var pair: cityMap.entrySet())
            normalisation.put(pair.getKey(), index++);
        Solution solution = new Solution();
        int[] father = new int[normalisation.size()];
        Arrays.fill(father, -1);
        int[] height = new int[normalisation.size()];
        for (var street: sortedStreetList) {

            int startIndex = normalisation.get(street.getStart());
            int endIndex = normalisation.get(street.getEnd());

            int componentStart = find(startIndex, father);
            int componentEnd = find(endIndex, father);

            if (componentStart != componentEnd) {
                union(componentStart, componentEnd, height, father);
                solution.setNodeStreet(street.getStart(), street);
                solution.setNodeStreet(street.getEnd(), street);
                solution.setStreet(street);
            }
        }
        return solution;
    }
}
