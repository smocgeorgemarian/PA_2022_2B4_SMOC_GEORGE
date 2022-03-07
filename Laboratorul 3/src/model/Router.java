package model;

public class Router extends Node implements Identifiable{
    private String address;

    public Router(String name, String macAddress, String address) {
        this.name = name;
        this.macAdress = macAddress;
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Router{" +
                "name='" + name + '\'' +
                ", macAddress='" + macAdress + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
