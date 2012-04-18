package edu.umn.pinkpanthers.beerfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for a Beer.
 * 
 */
public class Beer implements Parcelable, Comparable<Beer> {

    public static String SELECTED_BEER = "SELECTED_BEER";

    private String beerId;
    private String name;
    private String breweryName;
    private String hopsRank;
    private String bodyRank;
    private String colorRank;
    private String description;

    public static final Parcelable.Creator<Beer> CREATOR = new Parcelable.Creator<Beer>() {
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    public Beer(Parcel in) {
        readFromParcel(in);
    }

    public Beer() {
        // TODO
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(beerId);
        dest.writeString(name);
        dest.writeString(breweryName);
        dest.writeString(hopsRank);
        dest.writeString(bodyRank);
        dest.writeString(colorRank);
        dest.writeString(description);
    }

    private void readFromParcel(Parcel in) {
        beerId = in.readString();
        name = in.readString();
        breweryName = in.readString();
        hopsRank = in.readString();
        bodyRank = in.readString();
        colorRank = in.readString();
        description = in.readString();
    }

    public int compareTo(Beer otherBeer) {
        if (name != null && otherBeer.name != null) {
            return name.compareToIgnoreCase(otherBeer.name);
        }
        if (breweryName != null && otherBeer.breweryName != null) {
            return breweryName.compareToIgnoreCase(otherBeer.breweryName);
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getBreweryName() {
        return breweryName;
    }

}
