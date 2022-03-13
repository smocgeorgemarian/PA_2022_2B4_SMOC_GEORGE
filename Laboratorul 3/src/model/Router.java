package model;

import java.util.Objects;

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
                ", cost=" + getCostAdjacencyList() +
                ", macAdress='" + macAdress + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Router router = (Router) o;
        return Objects.equals(address, router.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address);
    }
}
