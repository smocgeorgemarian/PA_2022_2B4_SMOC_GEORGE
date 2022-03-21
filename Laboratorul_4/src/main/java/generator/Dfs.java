package generator;

import solver.Algorithm;
import solver.Solution;
import model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class Dfs {
    private Solution solution;

    public Dfs(Solution solution) {
        this.solution = solution;
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

    public CycleSolution solve() {
        List<Street> streets = solution.getStreets();
        Set<Intersection> used = new HashSet<>();
        CycleSolution cycleSolution = new CycleSolution();
        dfs(streets.get(0).getStart(), solution.getCityMap(), used, cycleSolution);
        return cycleSolution;
    }
}
