package com.app.savemycar.model;

import java.util.HashMap;

public class Issue {

    private String id;
    private String name;
    private HashMap<String, Primary> primaries;

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

    public HashMap<String, Primary> getPrimaries() {
        return primaries;
    }

    public void setPrimaries(HashMap<String, Primary> primaries) {
        this.primaries = primaries;
    }
}
