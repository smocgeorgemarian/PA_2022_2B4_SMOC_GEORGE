package generator;

import com.github.javafaker.Faker;
import model.City;
import model.Intersection;
import model.Street;

import java.util.*;
import java.util.stream.IntStream;

public class RandomTestsGenerator {
    private static final Random random = new Random();
    private static final Faker faker = new Faker();
    private static final int MAX_VALUE = 1000;
    public int getCitySize() {
        return random.nextInt(100) + 3;
    }

    public Intersection[] getNodes(int citySize) {
        return IntStream.range(0, citySize)
                .mapToObj(i -> new Intersection(faker.funnyName().name() + " " + i))
                .toArray(Intersection[]::new);
    }

    public Street getStreet(int fNodeIndex, int sNodeIndex, Intersection[] nodes, Map<StreetData, Integer> streetDataIntegerMap) {
        int maxValue = -1;
        for (int tmpIndex = 0; tmpIndex < fNodeIndex; tmpIndex++) {
            StreetData streetData1 = new StreetData(tmpIndex, fNodeIndex);
            StreetData streetData2 = new StreetData(tmpIndex, sNodeIndex);
            int value1 = streetDataIntegerMap.get(streetData1);
            int value2 = streetDataIntegerMap.get(streetData2);
            maxValue = (Math.max(maxValue, value1 + value2 + 1));
        }
        StreetData streetData = new StreetData(fNodeIndex, sNodeIndex);
        int length = random.nextInt(MAX_VALUE) + maxValue;
        streetDataIntegerMap.put(streetData, length);
        return new Street(faker.funnyName().name(), length, nodes[fNodeIndex], nodes[sNodeIndex]);
    }

    public Street[] getStreets(int citySize, Intersection[] nodes) {
        var streets = new Street[(citySize - 1) * citySize / 2];
        int streetIndex = 0;
        Map<StreetData, Integer> streetDataIntegerMap = new HashMap<>();
        for (int i = 0 ; i < citySize - 1; i++)
            for (int j = i + 1; j < citySize; j++) {
                streets[streetIndex] = getStreet(i, j, nodes, streetDataIntegerMap);
                streetIndex++;
            }
        return streets;
    }

    private boolean isUnused(Set<Intersection> used, Intersection node) {
        return !used.contains(node);
    }

    private void dfs(Intersection node, Map<Intersection, List<Street>> intersectionListMap, Set<Intersection> used,
                CycleSolution cycleSolution) {
        used.add(node);
        cycleSolution.addNode(node);
        for (Street street: intersectionListMap.get(node)) {
            Intersection end = street.getEnd();
            Intersection start = street.getStart();
            if (end != node && isUnused(used, end))
                dfs(end, intersectionListMap, used, cycleSolution);
            if (start != node && isUnused(used, start))
                dfs(start, intersectionListMap, used, cycleSolution);
        }
    }

    public CycleSolution getCycleSolution(int citySize) {
        Intersection[] nodes = getNodes(citySize);
        Street[] streets = getStreets(citySize, nodes);

        Map<Intersection, List<Street>> intersectionListMap = new HashMap<>();
        for (Intersection node: nodes) {
            intersectionListMap.put(node, new ArrayList<>());
        }
        for (Street street:  streets) {
            intersectionListMap.get(street.getStart()).add(street);
            intersectionListMap.get(street.getEnd()).add(street);
        }
        City
        return cycleSolution;
    }
}
