package pl.mborkowski.phones.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface PhonesServiceAsync {
    void get(AsyncCallback<ArrayList<Phone>> persons);
    void add(Phone p, AsyncCallback<ArrayList<Phone>> addCallback);
    void delete(int id, AsyncCallback<ArrayList<Phone>> persons);
    void find(int id, AsyncCallback<Phone> findCallback);
    void edit(int id, String brand, String model, int yearOfProduction, AsyncCallback<ArrayList<Phone>> persons);
}
