package pl.mborkowski.phones.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Phone implements IsSerializable{
	
	private String brand;
	private String model;
	private int yearOfProduction;
	public Phone(String brand, String model, int yearOfProduction) {
		super();
		this.brand = brand;
		this.model = model;
		this.yearOfProduction = yearOfProduction;
	}
	public Phone() {
		
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	
	
}
