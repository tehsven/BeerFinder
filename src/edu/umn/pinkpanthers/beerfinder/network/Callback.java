package edu.umn.pinkpanthers.beerfinder.network;

public interface Callback<X> {

    public void onSuccess(X value);

    public void onFailure(X value);

}
