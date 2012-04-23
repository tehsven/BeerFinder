package edu.umn.pinkpanthers.beerfinder.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import android.location.Location;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import edu.umn.pinkpanthers.beerfinder.data.Venue;

public class BeerFinderWebService {

    private final List<Beer> sortedBeerList = new ArrayList<Beer>();
    private final List<Venue> sortedVenueList = new ArrayList<Venue>();
    private static BeerFinderWebService instance;

    private BeerFinderWebService() {
        // TODO Implement some sort of "realistic" data
        
        // Generate some Beers
        int numberOfBeers = 50;
        for (int i = 0; i < numberOfBeers; i++) {
            Beer beer = new Beer(UUID.randomUUID().toString(), 
                    "Beer Name " + i, 
                    "Brewery " + i, 
                    "Hops Rank " + i,
                    "Body Rank " + i, 
                    "Color Rank " + i, 
                    "Description " + i);
            sortedBeerList.add(beer);
        }
        Collections.sort(sortedBeerList);

        // Generate some Venues
        int numberOfVenues = 10;
        List<Location> generatedLocations = LocationResolver.generateLocationsNear(
                LocationResolver.getDefaultLocation(), numberOfVenues);
        for (int i = 0; i < numberOfVenues; i++) {
            List<String> beerIds = new ArrayList<String>();
            for (int j = i*5; j < i*5+5; j++) {
                beerIds.add(String.valueOf(i));
            }
            Venue venue = new Venue(UUID.randomUUID().toString(), 
                    "Venue Name " + i, 
                    "Address " + i, 
                    "Phone Number " + i,
                    generatedLocations.get(i),
                    beerIds);
            sortedVenueList.add(venue);
        }
        Collections.sort(sortedVenueList);
    }
    
    public static void initialize() {
        if (instance == null) {
            instance = new BeerFinderWebService();
        }
    }
    
    public static BeerFinderWebService getInstance() {
        if (instance == null) {
            throw new RuntimeException("Cannot access uninitialized instance! Call initialize() first!");
        }
        return instance;
    }

    public List<Beer> getSearchableBeerList() {
        List<Beer> sortedBeerListCopy = new ArrayList<Beer>(sortedBeerList);
        return sortedBeerListCopy;
    }

    public List<Venue> getSearchableVenueList() {
        List<Venue> sortedVenueListCopy = new ArrayList<Venue>(sortedVenueList);
        return sortedVenueListCopy;
    }

}
