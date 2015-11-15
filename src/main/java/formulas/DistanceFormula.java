package formulas;

/**
 * Created by tmolloy on 14/11/2015.
 *
 * Interface for Great circle distance formulas
 */
public interface DistanceFormula {
    //From wikipedia - A good choice for the radius is the mean earth radius, approx 6371km
    public static final double EARTH_RADIUS = 6371;

    /**
     * Calculate the distance between to locations
     * @param p1
     * @param p2
     * @return distance in km
     */
    double distance(Location p1, Location p2);

}
