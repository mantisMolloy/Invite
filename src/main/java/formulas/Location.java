package formulas;

/**
 * Created by tmolloy on 14/11/2015.
 *
 * Represents position on earth using longitude and latitude given in degrees
 */
public class Location {
    private final double latitude;
    private final double longitude;

    /**
     * @param latitude in degrees not null
     * @param longitude in degrees not null
     */
    public Location(double latitude, double longitude){
        if(latitude < 0 || latitude > 90) throw new IllegalArgumentException("Latitude should be between 0 and 90 degrees");
        if(longitude < -180 || longitude > 180) throw new IllegalArgumentException("Longitude should be between -180 and 180 degrees");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString(){
        return "Latitide: " + getLatitude() + " Longitude: " + getLongitude();
    }
}
