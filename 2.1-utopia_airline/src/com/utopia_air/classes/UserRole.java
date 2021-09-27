package com.utopia_air.classes;

public class UserRole {

    private Integer id;
    private String name;

    public UserRole() {};

    public UserRole(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Role %d --> %s", this.id, this.name);
    }
}
