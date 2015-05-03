package pl.mborkowski.phones.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Phones implements EntryPoint {

	final FlexTable results = new FlexTable();

    final Label brandLabel = new Label("Marka");
    final Label modelLabel = new Label("Model");
    final Label yopLabel = new Label("Rok produkcji");

    final TextBox brand = new TextBox();
    final TextBox model = new TextBox();
    final TextBox yop = new TextBox();

    final Button add = new Button("Dodaj");
    final Button agree = new Button("Edytuj");
    HandlerRegistration handler;

	public void onModuleLoad() {
		 PhonesService.App.getInstance().get(new getPhonesCallback());

	        add.addClickHandler(new ClickHandler() {
	            @Override
	            public void onClick(ClickEvent event) {
	                Phone p = new Phone(brand.getValue(), model.getValue(), Integer.valueOf(yop.getValue()));
	                PhonesService.App.getInstance().add(p, new addCallback());
	            }
	        });

	        handler = agree.addClickHandler(new ClickHandler() {
	            @Override
	            public void onClick(ClickEvent event) {

	            }
	        });

	        agree.setEnabled(false);


	        // Assume that the host HTML has elements defined whose
	        // IDs are "slot1", "slot2".  In a real app, you probably would not want
	        // to hard-code IDs.  Instead, you could, for example, search for all
	        // elements with a particular CSS class and replace them with widgets.
	        //
	        RootPanel.get("form").add(brandLabel);
	        RootPanel.get("form").add(brand);
	        RootPanel.get("form").add(modelLabel);
	        RootPanel.get("form").add(model);
	        RootPanel.get("form").add(yopLabel);
	        RootPanel.get("form").add(yop);
	        RootPanel.get("form").add(add);
	        RootPanel.get("form").add(agree);

	        RootPanel.get("results").add(results);
	    }

	    private class getPhonesCallback implements AsyncCallback<ArrayList<Phone>> {

	        @Override
	        public void onSuccess(ArrayList<Phone> result) {
	        	addPhonesToResults(result);
	        }
	        @Override
	        public void onFailure(Throwable e) {
	            Window.alert(e.getMessage());
	        }
	    }

	    private class addCallback implements AsyncCallback<ArrayList<Phone>> {

	        @Override
	        public void onSuccess(ArrayList<Phone> result) {
	        	addPhonesToResults(result);
	        	cleanForm();
	        }
	        @Override
	        public void onFailure(Throwable e) {
	            Window.alert(e.getMessage());
	        }
	    }

	    private class deleteCallback implements AsyncCallback<ArrayList<Phone>> {

	        @Override
	        public void onSuccess(ArrayList<Phone> result) {
	        	addPhonesToResults(result);
	            add.setEnabled(true);
	            agree.setEnabled(false);
	        }
	        @Override
	        public void onFailure(Throwable e) {
	            Window.alert(e.getMessage());
	        }
	    }

	    private class editCallback implements AsyncCallback<ArrayList<Phone>>
	    {
	        @Override
	        public void onSuccess(ArrayList<Phone> result) {
	        	addPhonesToResults(result);
	            add.setEnabled(true);
	            agree.setEnabled(false);
	            cleanForm();
	        }

	        @Override
	        public void onFailure(Throwable e) {
	            Window.alert(e.getMessage());
	        }
	    }

	    private class findCallback implements AsyncCallback<Phone>
	    {
	        @Override
	        public void onSuccess(Phone result) {
	            agree.setEnabled(true);

	            brand.setText(result.getBrand());
	            model.setText(result.getModel());
	            yop.setText(String.valueOf(result.getYearOfProduction()));
	        }

	        @Override
	        public void onFailure(Throwable e) {
	            Window.alert(e.getMessage());
	        }
	    }

	    public void addPhonesToResults(ArrayList<Phone> phones)
	    {
	        results.removeAllRows();

	        results.setText(0, 0, "Marka");
	        results.setText(0, 1, "Model");
	        results.setText(0, 2, "Rok produkcji");
	        results.setText(0, 3, "Edytuj");
	        results.setText(0, 4, "Usun");


	        for(int i = 0; i< phones.size(); i++)
	        {
	            final int phoneId = i;
	            Phone p =  phones.get(i);

	            Button delete = new Button("Usun");
	            Button edit = new Button("Edytuj");

	            edit.addClickHandler(new ClickHandler() {
	                @Override
	                public void onClick(ClickEvent event) {

	                    PhonesService.App.getInstance().find(phoneId, new findCallback());

	                    add.setEnabled(false);

	                    handler.removeHandler();
	                    handler = agree.addClickHandler(new ClickHandler() {
	                        @Override
	                        public void onClick(ClickEvent event) {
	                            PhonesService.App.getInstance().edit(phoneId, brand.getText(), model.getText(), Integer.valueOf(yop.getText()), new editCallback());
	                        }
	                    });
	                }
	            });

	            delete.addClickHandler(new ClickHandler() {
	                @Override
	                public void onClick(ClickEvent event) {
	                    PhonesService.App.getInstance().delete(phoneId, new deleteCallback());
	                }
	            });

	            results.setText(i+1, 0, p.getBrand());
	            results.setText(i+1, 1, p.getModel());
	            results.setText(i+1, 2, String.valueOf((p.getYearOfProduction())));
	            results.setWidget(i+1, 3, edit);
	            results.setWidget(i+1, 4, delete);
	        }
	    }

	    public void cleanForm()
	    {
	        brand.setText("");
	        model.setText("");
	        yop.setText("");
	    }
}
