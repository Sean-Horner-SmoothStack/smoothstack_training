package com.utopia_air.classes;

public class Booking {

    private Integer id;
    private Boolean is_active;
    private String confirmation_code;

    public Booking() {};

    public Booking(Boolean is_active, String confirmation_code) {
        this.is_active = is_active;
        this.confirmation_code = confirmation_code;
    }

    public Booking(Integer id, Boolean is_active, String confirmation_code) {
        this.id = id;
        this.is_active = is_active;
        this.confirmation_code = confirmation_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getConfirmation_code() {
        return confirmation_code;
    }

    public void setConfirmation_code(String confirmation_code) {
        this.confirmation_code = confirmation_code;
    }

    public String toString() {
        return String.format("Booking ID: %d  Conf Code: %s",
                this.id, this.confirmation_code);
    }
}
