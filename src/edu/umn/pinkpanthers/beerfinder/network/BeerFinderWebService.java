package edu.umn.pinkpanthers.beerfinder.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import edu.umn.pinkpanthers.beerfinder.data.Beer;
import android.app.Activity;
import android.os.AsyncTask;

// TODO implement me
public class BeerFinderWebService {

    private static BeerFinderWebService _instance;
//    private HashMap<String, Beer> beers;
//    private HashMap<String, String> contactIdsMap = new HashMap<String, String>();

    private BeerFinderWebService() {
        // TODO
//        if (contacts == null) {
//            contacts = new HashMap<String, Beer>();

            // Get contacts from remote persistent storage and load into memory
//            Map<String, String> contactsJson = BeerFinderWebServiceWebService.readContacts();
//            for (Entry<String, String> contactJson : contactsJson.entrySet()) {
//                contacts.put(contactJson.getKey(), new Contact((String) contactJson.getValue()));
//                contactIdsMap.put(contactJson.getKey(), contactJson.getKey());
//            }
//        }
    }

    public static void initialize() {
        if (_instance == null) {
            _instance = new BeerFinderWebService();
        }
    }

    public static BeerFinderWebService getInstance(Activity activity) {
        return _instance;
    }

    public List<Beer> getSearchableBeers() {
        // TODO
//        List<Contact> sortedContacts = new ArrayList<Contact>(contacts.values());
//        Collections.sort(sortedContacts);
//        return sortedContacts;
        return Collections.<Beer>emptyList();
    }

//    public void removeContact(final Contact contact, final Callback<Void> callback) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                // Remove contact from local memory
//                contacts.remove(contact.getUUID());
//
//                // Remove contact from remote persistent storage
//                BeerFinderWebServiceWebService.deleteContact(contactIdsMap.get(contact.getUUID()));
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result) {
//                callback.onSuccess(result);
//            }
//        }.execute();
//    }
//
//    public void putContact(final Contact contact, final Callback<Void> callback) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                // Put contact in local memory
//                contacts.put(contact.getUUID(), contact);
//
//                // Update contact in remote persistent storage
//                if (contactIdsMap.containsKey(contact.getUUID())) {
//                    BeerFinderWebServiceWebService.updateContact(contactIdsMap.get(contact.getUUID()), contact.toJSON());
//                }
//                // Create contact in remote persistent storage
//                else {
//                    String remoteContactId = BeerFinderWebServiceWebService.createContact(contact.toJSON());
//                    contactIdsMap.put(contact.getUUID(), remoteContactId);
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result) {
//                callback.onSuccess(result);
//            }
//        }.execute();
//    }

}
