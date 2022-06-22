package com.example.market.model;

import java.util.List;

public class StatisticJson {
    List<Item> items;

    public StatisticJson(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public StatisticJson() {
    }
}
