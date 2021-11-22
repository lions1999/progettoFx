package logic.model;


public class Apartment {

	private String number;
	private String address;
	private String owner;
	private String resident;
	private String gas;
	private String water;
	private String energy;
	private String parking;

	public Apartment(String number,String address,String owner,String resident,String gas, String water, String energy, String parking) {
		this.number = number;
		this.address = address;
		this.owner = owner;
		this.resident = resident;
		this.gas = gas;
		this.water = water;
		this.energy = energy;
		this.parking = parking;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getResident() {
		return resident;
	}

	public String getGas() {return gas;}

	public void setGas(String gas) {this.gas = gas;}

	public String getWater() {return water;}

	public void setWater(String water) {this.water = water;}

	public String getEnergy() {return energy;}

	public void setEnergy(String energy) {this.energy = energy;}

	public String getParking() {return parking;}

	public void setParking(String parking) {this.parking = parking;}

}
