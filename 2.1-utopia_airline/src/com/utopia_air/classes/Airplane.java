package com.utopia_air.classes;

public class Airplane {

    private Integer id;
    private Integer type_id;

    public Airplane() {};

    public Airplane(Integer ID, Integer type) {
        this.id = ID;
        this.type_id = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String toString() {
        return String.format("Plane %d --> type %d", this.id, this.type_id);
    }
}
