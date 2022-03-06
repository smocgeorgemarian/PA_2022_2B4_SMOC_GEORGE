package lab.solver;

/**
 * Abstract class used for instanciating objects that solve the problem.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public interface Algorithm {
    /**
     * Solves the problem.
     * Astract method implemented in DSaturAlgorithm and GreedyAlgorithm.
     * @return The <code>Solution</code> found by the algorithm.
     */
    Solution solve();
}
