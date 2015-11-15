package formulas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.*;

/**
 * Created by tmolloy on 14/11/2015.
 *
 * Object which implements great circle distance using law of cosines
 */
public class LawOfCosines implements DistanceFormula {

    private static final Logger logger = LoggerFactory.getLogger(LawOfCosines.class);

    @Override
    public double distance(Location p1, Location p2) {

        if(p1 ==null || p2 == null) throw new IllegalArgumentException("Null not a valid argument");

        double sinOmega_1 = sin(toRadians(p1.getLatitude()));
        double sinOmega_2 = sin(toRadians(p2.getLatitude()));
        double cosOmega_1 = cos(toRadians(p1.getLatitude()));
        double cosOmega_2 = cos(toRadians(p2.getLatitude()));

        double delataLamda = toRadians(abs(p1.getLongitude() - p2.getLongitude()));
        double centralAngle = acos((sinOmega_1 * sinOmega_2) + (cosOmega_1 * cosOmega_2 * cos(delataLamda)));
        double distance = EARTH_RADIUS*centralAngle;

        logger.debug("Distance between " + p1 + " and " + p2 + " = " + distance);

        return distance;
    }
}
