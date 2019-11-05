/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joan Jara and Aleix Iglesias
 */
@Entity
@Table(name = "ROOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT c FROM Room c"),
    @NamedQuery(name = "Room.findByRoomId", query = "SELECT c FROM Customer c WHERE c.roomID = :roomID"),
    @NamedQuery(name = "Room.findBySimple", query = "SELECT c FROM Room c WHERE c.simple = :simple"),
    @NamedQuery(name = "Room.findByCity", query = "SELECT c FROM Room c WHERE c.city = :city"),
    @NamedQuery(name = "Room.findByPrice", query = "SELECT c FROM Room c WHERE c.price = :price"),
    @NamedQuery(name = "Room.findByAge", query = "SELECT c FROM Room c WHERE c.age = :age"),
    @NamedQuery(name = "Room.findByNotIndoor", query = "SELECT c FROM Room c WHERE c.indoor <> :indoor"),
    @NamedQuery(name = "Room.findByNotSmoker", query = "SELECT c FROM Room c WHERE c.smoker <> :smoker"),
    @NamedQuery(name = "Room.findByNotPet", query = "SELECT c FROM Room c WHERE c.pet <> :pet"),
    @NamedQuery(name = "Room.findByPet", query = "SELECT c FROM Room c WHERE c.pet = :pet"),
    @NamedQuery(name = "Room.findByIndoor", query = "SELECT c FROM Room c WHERE c.indoor = :indoor"),
    @NamedQuery(name = "Room.findBySmoker", query = "SELECT c FROM Room c WHERE c.smoker = :smoker"),
    @NamedQuery(name = "Room.findByFurnished", query = "SELECT c FROM Room c WHERE c.furnished = :furnished")})
public class Room implements Serializable{
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROOM_ID")
    private Integer roomID;
    @Size(max = 400)
    @Column(name = "DESCRIPTION")
    private String description = "";
    @Size(max = 30)
    @Column(name = "CITY")
    private String city = "";
    @Column(name = "PRICE")
    private Float price = 0.0f;
    @Column(name = "AGE")
    private Integer age = 0;
    @Column(name = "SIMPLE")
    private Boolean simple = false;
    @Column(name = "INDOOR")
    private Boolean indoor = false;
    @Column(name = "FURNISHED")
    private Boolean furnished = false;
    @Column(name = "SMOKER")
    private Boolean smoker = false;
    @Column(name = "PET")
    private Boolean pet = false;

    public Room()
    {
        
    }
    
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
    
    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }  
    
    public Integer getAget() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }  
    
}
