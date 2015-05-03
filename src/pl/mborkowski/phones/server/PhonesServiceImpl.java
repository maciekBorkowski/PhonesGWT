package pl.mborkowski.phones.server;

import java.util.ArrayList;

import pl.mborkowski.phones.client.Phone;
import pl.mborkowski.phones.client.PhonesService;
import pl.mborkowski.phones.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
public class PhonesServiceImpl extends RemoteServiceServlet implements PhonesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Phone> phones = new ArrayList<Phone>();

	public ArrayList<Phone> get() {
		return phones;
	}

	public ArrayList<Phone> add(Phone p) {
		phones.add(p);
		return phones;
	}

	public ArrayList<Phone> delete(int id) {
		phones.remove(id);
		return phones;
	}

	public Phone find(int id) {
		return phones.get(id);
	}

	public ArrayList<Phone> edit(int id, String brand, String model, int yearOfProduction) {
		phones.get(id).setBrand(brand);
		phones.get(id).setModel(model);
		phones.get(id).setYearOfProduction(yearOfProduction);

		return phones;
	}
}
