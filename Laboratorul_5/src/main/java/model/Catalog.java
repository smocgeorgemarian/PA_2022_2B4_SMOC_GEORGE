package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Item> itemList = new ArrayList<>();

    public Catalog() {}

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog(String name, List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void add(Item item) {
        itemList.add(item);
    }
    public Item findById(String id) {
        return itemList.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
