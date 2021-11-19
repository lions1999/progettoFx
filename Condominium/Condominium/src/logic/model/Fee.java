package logic.model;

public class Fee {

    private String apartment;
    private Double water;
    private Double gas;
    private Double elect;
    private Double admin;
    private Double park;
    private Double elevator;
    private Double pet;
    private Double wifi;
    private boolean availablePark;
    private boolean availableElevator;
    private boolean availablePet;
    private boolean availableWifi;


    public Fee(String apartment, Double water, Double gas, Double elect, Double admin, Double park, Double elevator, Double pet, Double wifi) {
        this.apartment = apartment;
        this.water = water;
        this.gas = gas;
        this.elect = elect;
        this.admin = admin;
        this.park = park;
        this.elevator = elevator;
        this.pet = pet;
        this.wifi = wifi;
    }

    public Fee(boolean park,boolean elevator,boolean pet,boolean wifi){
        this.availablePark = park;
        this.availableElevator = elevator;
        this.availablePet = pet;
        this.availableWifi = wifi;
    }

    public String getApt() {
        return apartment;
    }

    public void setApt(String apartment) {
        this.apartment = apartment;
    }
    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public Double getElect() {
        return elect;
    }

    public void setElect(Double elect) {
        this.elect = elect;
    }

    public Double getAdmin() {
        return admin;
    }

    public void setAdmin(Double admin) {
        this.admin = admin;
    }

    public Double getPark() {
        return park;
    }

    public void setPark(Double park) {
        this.park = park;
    }

    public Double getElevator() {
        return elevator;
    }

    public void setElevator(Double elevator) {
        this.elevator = elevator;
    }

    public Double getPet() {
        return pet;
    }

    public void setPet(Double pet) {
        this.pet = pet;
    }

    public Double getWifi() {
        return wifi;
    }

    public void setWifi(Double wifi) {
        this.wifi = wifi;
    }

    public boolean getAvailablePark(){
        return availablePark;
    }
    public boolean getAvailableElevator(){
        return availableElevator;
    }
    public boolean getAvailablePet(){
        return availablePet;
    }
    public boolean getAvailableWifi(){
        return availableWifi;
    }
}
