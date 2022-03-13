package model;

import java.util.Objects;

public class Computer extends Node implements Identifiable, Storage {
    private String address;
    private int storageCapacity;

    public Computer(String name, String macAddress, String address, int storageCapacity) {
        this.name = name;
        this.macAdress = macAddress;
        this.address = address;
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "address='" + address + '\'' +
                ", storageCapacity=" + storageCapacity +
                ", name='" + name + '\'' +
                ", cost=" + getCostAdjacencyList() +
                ", macAdress='" + macAdress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return storageCapacity == computer.storageCapacity && Objects.equals(address, computer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, storageCapacity);
    }
}
