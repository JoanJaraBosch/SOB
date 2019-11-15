/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Room.findByRoomId", query = "SELECT c FROM Room c WHERE c.roomID = :roomID"),
    @NamedQuery(name = "Room.findBySimple", query = "SELECT c FROM Room c WHERE c.simple = :simple"),
    @NamedQuery(name = "Room.findByCity", query = "SELECT c FROM Room c WHERE c.city = :city"),
    @NamedQuery(name = "Room.findByPrice", query = "SELECT c FROM Room c WHERE c.price = :price"),
    @NamedQuery(name = "Room.findByAdreça", query = "SELECT c FROM Room c WHERE c.adreça = :adreça"),
    @NamedQuery(name = "Room.findByIndoor", query = "SELECT c FROM Room c WHERE c.indoor = :indoor"),
    @NamedQuery(name = "Room.findByZip", query = "SELECT c FROM Room c WHERE c.zip = :zip"),
    @NamedQuery(name = "Room.findByFurnished", query = "SELECT c FROM Room c WHERE c.furnished = :furnished")})
public class Room implements Serializable, Comparable<Room>{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROOM_ID")
    private Integer roomID;
    @Column(name = "ZIPCODE")
    private Integer zip;        
    @Size(max = 400)
    @Column(name = "DESCRIPTION")
    private String description = "";
    @Column(name = "ADREÇA")
    private String adreça = "";
    @Size(max = 30)
    @Column(name = "CITY")
    private String city = "";
    @Column(name = "PRICE")
    private Float price = 0.0f;
    @Column(name = "SIMPLE_ROOM")
    private Boolean simple = false;
    @Column(name = "INDOOR")
    private Boolean indoor = false;
    @Column(name = "FURNISHED")
    private Boolean furnished = false;
     
    
    @OneToOne
    @JoinColumn(name = "RENTER_ID")
    private Renter renter;
    
    @OneToOne
    @JoinColumn(name = "TENANT_ID")
    private Tenant tenant;
    
    public Room(Integer id,Integer zip, String adreça,String description,String city ,Float price, Boolean simple,Boolean indoor,Boolean furnished,Boolean smoker,Integer age, Boolean pet) {
        this.roomID=id;
        this.description=description;
        this.city=city;
        this.price=price;
        this.simple=simple;
        this.indoor=indoor;
        this.furnished=furnished;
        this.adreça=adreça;
        this.zip=zip;
    }
    
    public Room()
    {
        
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
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

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    

    public int compareTo(Room t) {
        return Comparators.PRICECOMP.compare(this, t);
    }
    
     public static class Comparators {
        public static Comparator<Room> PRICECOMP = new Comparator<Room>() {
            @Override
            public int compare(Room t, Room t1) {
                if(t.getPrice()==t1.getPrice()){
                    return 0;
                }else if(t.getPrice()<t1.getPrice()){
                    return -1;
                }else{
                    return 1;
                }
            }
           };
     }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", zip=" + zip + ", description=" + description + ", adre\u00e7a=" + adreça + ", city=" + city + ", price=" + price + ", simple=" + simple + ", indoor=" + indoor + ", furnished=" + furnished + '}';
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
    
    public Integer maxID(List<Room> rooms){
        Integer id=0;
        for(Room a:rooms){
            if(a.getRoomID()>id) id = a.getRoomID();
        }
        return id;
    }
}
