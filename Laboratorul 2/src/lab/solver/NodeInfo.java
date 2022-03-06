package lab.solver;

/**
 * Record used to keep together information about the saturation degree of an object,
 * its degree in the remaining graph after choosing some objects and index of the event that is tried to
 * be matched with a room.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public record NodeInfo(int saturationDegree, int degree, int index) {

}