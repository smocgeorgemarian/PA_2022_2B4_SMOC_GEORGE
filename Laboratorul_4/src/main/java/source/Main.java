package source;

import com.github.javafaker.Faker;
import generator.RandomTestsGenerator;
import model.City;
import model.Intersection;
import model.Street;
import solver.Algorithm;
import solver.PrimAlgorithm;
import solver.Solution;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static final float MIN_VALUE = (float) 1.1;
    public static Street[] createStreets(Intersection[] nodes) {
        Street[] streets = new Street[16];
        streets[0] = new Street("s0", 2, nodes[0], nodes[1]);
        streets[1] = new Street("s1", 2, nodes[0], nodes[2]);
        streets[2] = new Street("s2", 2, nodes[1], nodes[2]);

        streets[3] = new Street("s3", 3, nodes[1], nodes[8]);
        streets[4] = new Street("s4", 2, nodes[2], nodes[4]);
        streets[5] = new Street("s5", 1, nodes[2], nodes[3]);
        streets[6] = new Street("s6", 2, nodes[0], nodes[3]);
        streets[7] = new Street("s7", 3, nodes[3], nodes[4]);
        streets[8] = new Street("s8", 3, nodes[4], nodes[5]);
        streets[9] = new Street("s9", 1, nodes[5], nodes[6]);
        streets[10] = new Street("s10", 1, nodes[5], nodes[7]);
        streets[11] = new Street("s11", 1, nodes[6], nodes[7]);
        streets[12] = new Street("s12", 1, nodes[4], nodes[8]);
        streets[13] = new Street("s13", 2, nodes[2], nodes[6]);
        streets[14] = new Street("s14", 2, nodes[5], nodes[8]);
        streets[15] = new Street("s15", 1, nodes[7], nodes[8]);
        return streets;
    }
    public static City createSetupForExample(Intersection[] nodes, Street[] streets) {
        City city = new City();
        city.setIntersectionData(nodes[0], Arrays.asList(streets[0], streets[1], streets[6]));
        city.setIntersectionData(nodes[1], Arrays.asList(streets[0], streets[2], streets[3]));
        city.setIntersectionData(nodes[2], Arrays.asList(streets[1], streets[2], streets[4], streets[5], streets[13]));
        city.setIntersectionData(nodes[3], Arrays.asList(streets[5], streets[6], streets[7]));
        city.setIntersectionData(nodes[4], Arrays.asList(streets[4], streets[7], streets[8]));
        city.setIntersectionData(nodes[5], Arrays.asList(streets[8], streets[9], streets[10], streets[14]));
        city.setIntersectionData(nodes[6], Arrays.asList(streets[9], streets[11], streets[13]));
        city.setIntersectionData(nodes[7], Arrays.asList(streets[10], streets[11], streets[15]));
        city.setIntersectionData(nodes[8], Arrays.asList(streets[3], streets[12], streets[14], streets[15]));
        return city;
    }

    public static void setRandomNames(Street[] streets, Set<Intersection> intersectionSet) {
        Faker faker = new Faker();
        for(var street : streets)
            street.setName(faker.funnyName().name());
        for (var intersection: intersectionSet)
            intersection.setName(faker.funnyName().name());
    }
    public static void setRandomTestsPrintable() {
        for (int testIndex = 0; testIndex < 10; testIndex++) {
            RandomTestsGenerator randomTestsGenerator = new RandomTestsGenerator();
            int cs = randomTestsGenerator.getCitySize();
            City city = randomTestsGenerator.getCity(cs);

            Algorithm algorithm = new PrimAlgorithm(city);
            Solution solution = algorithm.solve();
            System.out.println("[random test " + testIndex + "] The streets chosen for APM:\n" + solution);
        }
    }

    public static void main(String[] args) {
        var nodes = IntStream.range(0, 9)
                .mapToObj(i -> new Intersection("v" + i))
                .toArray(Intersection[]::new);

        Street[] streets = createStreets(nodes);
        List<Street> streetList = new LinkedList<>(Arrays.asList(streets));
        List<Street> newStreetList = streetList.stream()
                .sorted(Comparator.comparing(Street::getLength)).toList();
        System.out.println("Sorted streets:\n" + newStreetList);

        List<Intersection> nodeList = new ArrayList<>(Arrays.asList(nodes));
        nodeList.add(nodeList.get(0));
        Set<Intersection> intersectionSet = new HashSet<>(nodeList);
        System.out.println("Intersections without duplicates:\n" + intersectionSet);

        City city = createSetupForExample(nodes, streets);
        System.out.println("Streets that respect the condition:");
        Map<Intersection, List<Street>> cityMap = city.getCityMap();
        streetList.stream()
                .filter(v -> v.getLength() > MIN_VALUE && cityMap.get(v.getStart()).size() + cityMap.get(v.getEnd()).size() > 3)
                .forEach(System.out::println);

        setRandomNames(streets, intersectionSet);
        System.out.println("City with random names:\n" + city);
        Algorithm algorithm = new PrimAlgorithm(city);
        Solution solution = algorithm.solve();
        System.out.println("The streets chosen for APM:\n" + solution);

        setRandomTestsPrintable();
    }
}
