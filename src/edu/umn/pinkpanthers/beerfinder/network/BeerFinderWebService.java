package edu.umn.pinkpanthers.beerfinder.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.android.maps.GeoPoint;

import android.location.Location;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import edu.umn.pinkpanthers.beerfinder.data.Venue;

public class BeerFinderWebService {
	// stub out the different beers
	public static Beer beer_list[] = {
		new Beer(
			"1",
			"Oatmeal Stout",	// name
			"Summit", 			// brewery
			"3", 				// hops
			"8", 				// body
			"10",	 			// color
			"While its cascading rich black color will be familiar to Stout enthusiasts, ours is decidedly different. It's made with naked oats from the UK, for one. Smooth and slightly sweet, with hints of coffee, caramel and chocolate. Sold only on draught in select places."  // description
		),new Beer(
			"2",
			"Horizon Red Ale",	// name
			"Summit", 			// brewery
			"8", // hops
			"6", // body
			"6", // color
			"An inspired hybrid that blurs the boundaries between IPA and Amber styles. The exceptional blend of American hops (including the harder-to-find Horizon variety) gives it an intense pine, citrus and earthy character in the nose and on the tongue."  // description
		),new Beer(
			"3",
			"Extra Pale Ale",	// name
			"Summit", // brewery
			"5", // hops
			"6", // body
			"4", // color
			"A pioneer in craft beer (hey, that rhymes), Summit EPA has been gracing the pint glasses of serious brew lovers since 1986. Bronze color. Gold medal-winning flavor. With caramel, biscuity malts superbly balanced with an earthy hop bite and juicy citrus."  // description
		),new Beer(
			"4",
			"Maibock",	// name
			"Summit", // brewery
			"3", // hops
			"8", // body
			"2", // color
			"This traditional German style earned the nickname “liquid bread” because it was brewed by monks to help them get through Lenten fasting. Moravian 37 malts and Czech Saaz hops give it a toasted sweetness with a little spice in the finish."  // description
		),new Beer(
			"5",
			"Furious",	// name
			"Surly", // brewery
			"7", // hops
			"5", // body
			"3", // color
			"A tempest on the tongue, or a moment of pure hop bliss? Brewed with a dazzling blend of American hops and Scottish malt, this crimson-hued ale delivers waves of citrus, pine and caramel-toffee. For those who favor flavor, Furious has the hop-fire your taste buds have been screeching for."  // description
		),new Beer(
			"6",
			"Coffee Bender",	// name
			"Surly", // brewery
			"3", // hops
			"6", // body
			"9", // color
			"Coffee Bender refreshes like an iced-coffee, is aromatic as a bag of whole beans as satisfies like your favorite beer. The Surly brew team has developed a cold extraction process that results in intense coffee aromatics and flavor bringing together two of our favorite beverages. Your only dilemma will be whether to finish your day or start it with a Coffee Bender."  // description
		),new Beer(
			"7",
			"CynicAle",	// name
			"Surly", // brewery
			"6", // hops
			"6", // body
			"1", // color
			"Oh great, a fizzy yellow beer in a can, that's just what the craft beer world needs! CynicAle melds Old World ingredients in a new school style. French malted barley, English oats and Belgian yeast create honey & black pepper flavors. Lively Slovenian hops provide the floral, apricot and peach notes. Toss your doubts away, toss one back and enjoy!"  // description
		),new Beer(
			"8",
			"Belgian White",	// name
			"Blue Moon Brewing Co", // brewery
			"2", // hops
			"7", // body
			"5", // color
			"They've been brewing cloudy beer in Belgium for more than 300 years now. This unfiltered style of ale combines malt, wheat and oats, giving the belgian white its signature cloudy appearance and smooth, full-bodied taste."  // description
		),new Beer(
			"9",
			"Winter Abbey Ale",	// name
			"Blue Moon Brewing Co.", // brewery
			"1", // hops
			"5", // body
			"5", // color
			"An abbey ale crafted with roasted malts, hint of Belgian sugar, and a touch of wheat. Rich caramel and toffee notes. Pair with hearty stews and chocolate desserts."  // description	
		),new Beer(
			"10",
			"North West Passage",	// name
			"Flat Earth", // brewery
			"9", // hops
			"4", // body
			"7", // color
			"The hoppiest IPA in the Midwest at 115 IBU! The Northwest Passage, once a virtually impassable route through Canada it has become the \"secret\" submarine route. Made with Canadian malts and four American hops, Northwest Passage goes well with Pho, Buffalo wings & bon fires"  // description
		),new Beer(
			"11",
			"Guinness",	// name
			"Diageo", // brewery
			"4", // hops
			"8", // body
			"10", // color
			"Swirling clouds tumble as the storm begins to calm, settle, breathe in the moment, then break through the smooth, light head to the bittersweet reward. Unmistakeably GUINNESS® beer, stout, or draught , from the first velvet sip to the last, lingering drop. And every deep-dark satisfying mouthful in between. Pure beauty."  // description
		),new Beer(
			"12",
			"Stella Artois",	// name
			"Stella Artois", // brewery
			"3", // hops
			"4", // body
			"1", // color
			"Stella Artois is one of the world’s best-selling beers and is enjoyed in more than 80 countries. Its full, characteristic flavour and high quality is assured through a superior brewing process and by using the finest ingredients available."  // description
		)
	};
	
	// stub out 5 different Venues
	public static Venue venue_list[] = {
		new Venue(
			"1",
			"Sally's Salon & Eatery",
			"712 Washington Ave. SE, Minneapolis MN",
			"612.331.3231",
			new GeoPoint((int)(1000000 * 44.973628), (int)(1000000 * -93.228087)),
			new ArrayList <String>(Arrays.asList("1","2","3","4","5","6","7","8","9","10"))
		),
		new Venue(
			"2",
			"Stub & Herb",
			"227 SE Oak St \nMinneapolis, MN 55455",
			"612.379.0555",
			new GeoPoint((int)(1000000 * 44.973804), (int)(1000000 * -93.226958)),
			new ArrayList <String>(Arrays.asList("6","7","8","9","10","11","12","13","14","15")) 
			
		),
		new Venue(
			"3",
			"Kitty Cat Klub",
			"315 14th Avenue SE \nMinneapolis, MN 55414",
			"612.331.9800",
			new GeoPoint((int)(1000000 * 44.980091),(int)(1000000 * -93.236325)),
			new ArrayList <String>(Arrays.asList("1","2","3","13","14","15"))
		),
		new Venue(
			"4",
			"TCF Stadium",
			"2009 University Ave SE \nMinneapolis, MN 55455",
			"n/a",
			new GeoPoint((int)(1000000 * 44.976532), (int)(1000000 * -93.224552)),
			new ArrayList <String>(Arrays.asList("1,5,10,15"))
		),
		new Venue(
			"5",
			"Minneapolis Town Hall Brewery  ",
			"1430 South Washington Ave \nMinneapolis, MN 55454",
			"612.339.8696",
			new GeoPoint((int)(1000000 * 44.973132), (int)(1000000 * -93.247694)),
			new ArrayList <String>(Arrays.asList("2","4","6","8","10","12","14"))
		)
	};
	
    private final List<Beer> sortedBeerList = new ArrayList<Beer>();
    private final List<Venue> sortedVenueList = new ArrayList<Venue>();
    private static BeerFinderWebService instance;

    private BeerFinderWebService() {
        // TODO Implement some sort of "realistic" data
        
        //Add defined beers to sortedBeerList
        for (int i = 0; i < beer_list.length; i++) {
            
            sortedBeerList.add(beer_list[i]);
        }
        Collections.sort(sortedBeerList);

        // Add defined venues to sortedVenueList
        for (int i = 0; i < venue_list.length; i++)
        {
        	sortedVenueList.add(venue_list[i]);
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
