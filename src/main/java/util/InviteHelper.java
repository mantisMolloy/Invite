package util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import formulas.LawOfCosines;
import formulas.DistanceFormula;
import formulas.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

/**
 * Created by tmolloy on 14/11/2015.
 *
 * Helper utility for issuing invites to party
 */
public class InviteHelper {

    private static final Logger logger = LoggerFactory.getLogger(InviteHelper.class);

    private static final double PARTY_LATITUDE = 53.3381985;
    private static final double PARTY_LONGITUDE = -6.2592576;

    private DistanceFormula distanceStrategy;
    private Location location;

    /**
     * Set default distanceStrategy to lawOfCosines
     * Set default location to Party location
     */
    public InviteHelper(){
        distanceStrategy = new LawOfCosines();
        location = new Location(PARTY_LATITUDE, PARTY_LONGITUDE);
    }

    /**
     *  Map a json encoded string to an Optional<Person>
     *  All values must be present in json string.
     *  null b#values not allowed.
     *  unknown properties not allowed.
     * @param jsonString not null
     * @return Optional<Person>
     */
    public Optional<Person> json2Person(String jsonString){
        Optional<Person> person = Optional.empty();

        ObjectMapper mapper = new ObjectMapper()
                                    .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                                    .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                                    .enable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES);
        try {
            person = Optional.of(mapper.readValue(jsonString, Person.class));
        } catch (JsonGenerationException e) {
            logger.error("Error processing JsonString: " + jsonString, e);
        } catch (JsonMappingException e) {
            logger.error("Error processing JsonString: " + jsonString, e);
        } catch (JsonProcessingException e){
            logger.error("Error processing JsonString: " + jsonString, e);
        } catch (Exception e) {
            logger.error("Error processing JsonString: " + jsonString, e);
        }
        person.ifPresent(p -> logger.debug("\nCreated Person:\n" + p + "\n\nUsing jsonString " + jsonString));
        return person;
    }

    /**
     * check if a person is within a certain location of the helpers currently
     * set location (default party location)
     * @param person
     * @param requiredDistance in km
     * @return boolean
     */
    public Boolean isWithin(Person person, double requiredDistance){
        Location personsLocation;
        try {
            personsLocation = new Location(person.getLatitude(), person.getLongitude());
        } catch (IllegalArgumentException e){
            logger.error("Error creating location",e);
            return false;
        } catch (Exception e){
            logger.error("Error creating location",e);
            return false;
        }
        double dist = distanceStrategy.distance(personsLocation, location);

        logger.debug(person.getName() + " is " + dist + "Km from party");
        return dist < requiredDistance;
    }

    public DistanceFormula getDistanceStrategy() {
        return distanceStrategy;
    }

    /**
     *
     * @param distanceStrategy not null
     */
    public void setDistanceStrategy(DistanceFormula distanceStrategy) {
        if(distanceStrategy == null) throw new IllegalArgumentException("Null not a valid argument");
        this.distanceStrategy = distanceStrategy;
    }

    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location not null
     */
    public void setLocation(Location location) {
        if(location == null) throw new IllegalArgumentException("Null not a valid argument");
        this.location = location;
    }
}


