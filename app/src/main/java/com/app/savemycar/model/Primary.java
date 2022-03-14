package com.app.savemycar.model;

import java.util.HashMap;

public class Primary {

    private String id;
    private String name;
    private HashMap<String, Secondary> secondaries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Secondary> getSecondaries() {
        return secondaries;
    }

    public void setSecondaries(HashMap<String, Secondary> secondaries) {
        this.secondaries = secondaries;
    }
}
