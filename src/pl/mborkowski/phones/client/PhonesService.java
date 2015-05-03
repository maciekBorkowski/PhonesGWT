package pl.mborkowski.phones.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("phones")
public interface PhonesService extends RemoteService {
	   // Sample interface method of remote interface
    ArrayList<Phone> get();
    ArrayList<Phone> add(Phone p);
    ArrayList<Phone> delete(int id);
    Phone find(int id);
    ArrayList<Phone> edit(int id, String brand, String model, int yearOfProduction);

    public static class App {
        private static PhonesServiceAsync ourInstance = GWT.create(PhonesService.class);

        public static synchronized PhonesServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
