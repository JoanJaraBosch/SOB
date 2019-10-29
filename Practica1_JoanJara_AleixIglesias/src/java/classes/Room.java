/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Joan Jara and Aleix Iglesias
 */
public class Room {
    private String description = "";
    private String city = "";
    private Float price = 0.0f;
    private Boolean simple = false;
    private Boolean indoor = false;
    private Boolean furnished = false;
    private Boolean smoker = false;

    public Room(String description,String city ,Float price, Boolean simple,Boolean indoor,Boolean furnished,Boolean smoker) {
        this.description=description;
        this.city=city;
        this.price=price;
        this.simple=simple;
        this.indoor=indoor;
        this.furnished=furnished;
        this.smoker=smoker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getSimple() {
        return simple;
    }

    public void setSimple(Boolean simple) {
        this.simple = simple;
    }

    public Boolean getIndoor() {
        return indoor;
    }

    public void setIndoor(Boolean indoor) {
        this.indoor = indoor;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }  
}
