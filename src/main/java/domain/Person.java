package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tmolloy on 14/11/2015.
 *
 * Immutable Person class to represent Json object.
 * All Properties must be present in json object to create this object.
 */
public class Person implements Comparable<Person> {

    private final String name;
    private final int user_id;
    private final double latitude;
    private final double longitude;
    // Lazily initialized, cached hashCode
    private volatile int hashCode;


    /**
     *
     * @param name not null
     * @param user_id
     * @param latitude in degrees
     * @param longitude in degrees
     */
    @JsonCreator
    public Person(@JsonProperty("name") String name,
                  @JsonProperty("user_id") int user_id,
                  @JsonProperty("latitude") double latitude,
                  @JsonProperty("longitude") double longitude)
    {
        if(name == null) throw new IllegalArgumentException("Null not a valid argument"); //ints & doubles cannot be null
        this.name = name;
        this.user_id = user_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public int getUser_id() {
        return user_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * compare to on user_id to allow easy sorting
     * @param o
     * @return
     */
    public int compareTo(Person o) {
        return this.getUser_id() - o.getUser_id();
    }

    /**
     * Compare all fileds of object and type equality
     * @param o
     * @return
     */
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person))
            return false;
        Person p = (Person)o;
        return p.getName().equals(getName())
                && p.getUser_id()  == getUser_id()
                && p.getLatitude() == getLatitude()
                && p.getLongitude() == getLongitude();
    }

    /**
     * Not used in hasmap but good practice to implement hashcode when implementing equals
     * @return
     */
    @Override public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = 31 * result + getUser_id();
            result = 31 * result + Double.valueOf(getLongitude()).hashCode();
            result = 31 * result + Double.valueOf(getLatitude()).hashCode();
            result = 31 * result + (null == getName() ? 0: getName().hashCode());
            hashCode = result;
        }
        return result;
    }

    @Override
    public String toString(){
        return "ID: " + getUser_id() + "\nName: " + getName() + "\nLongitude: " + getLongitude() + "\nLatitude: " + getLatitude();
    }
}
