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
 * @author Joan Jara
 */
@Entity
@Table(name = "RENTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Renter.findAll", query = "SELECT c FROM Renter c"),
    @NamedQuery(name = "Renter.findByRoomId", query = "SELECT c FROM Renter c WHERE c.id = :id"),
    @NamedQuery(name = "Renter.findByAdreça", query = "SELECT c FROM Renter c WHERE c.name = :name"),
    @NamedQuery(name = "Renter.findByName", query = "SELECT c FROM Renter c WHERE c.surname = :surname"),
    @NamedQuery(name = "Renter.findBySurname", query = "SELECT c FROM Renter c WHERE c.adreça = :adreça"),
    @NamedQuery(name = "Renter.findByEmail", query = "SELECT c FROM Renter c WHERE c.email = :email"),
    @NamedQuery(name = "Renter.findByZip", query = "SELECT c FROM Renter c WHERE c.zip = :zip"),
    @NamedQuery(name = "Renter.findByCity", query = "SELECT c FROM Renter c WHERE c.city = :city"),
    @NamedQuery(name = "Renter.findByAgeMax", query = "SELECT c FROM Renter c WHERE c.agemax = :agemax"),
    @NamedQuery(name = "Renter.findByAgeMin", query = "SELECT c FROM Renter c WHERE c.agemin = :agemin"),
    @NamedQuery(name = "Renter.findByPet", query = "SELECT c FROM Renter c WHERE c.pet = :pet"),
    @NamedQuery(name = "Renter.findBySmoker", query = "SELECT c FROM Renter c WHERE c.smoker = :smoker")})
public class Renter implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ZIP")
    private Integer zip;        
    @Size(max = 400)
    @Column(name = "EMAIL")
    private String email = "";
    @Size(max = 400)
    @Column(name = "NAME")
    private String name = "";
    @Size(max = 400)
    @Column(name = "SURNAME")
    private String surname = "";
    @Column(name = "ADREÇA")
    private String adreça = "";
    @Size(max = 30)
    @Column(name = "CITY")
    private String city = "";
    @Column(name = "AGE_MAX")
    private Integer agemax = 100;
    @Column(name = "AGE_MIN")
    private Integer agemin = 0;
    @Column(name = "SMOKER")
    private Boolean smoker = false;
    @Column(name = "PET")
    private Boolean pet = false;
/*
    @OneToOne(mappedBy="renter")
    @JoinColumn(name = "ROOM_ID_FOREIGN")
    private Room room;
   */ 
    public Renter(Integer id, Integer zip) {
        this.id = id;
        this.zip = zip;
    }

    public Renter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAgemax() {
        return agemax;
    }

    public void setAgemax(Integer agemax) {
        this.agemax = agemax;
    }

    public Integer getAgemin() {
        return agemin;
    }

    public void setAgemin(Integer agemin) {
        this.agemin = agemin;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Renter{" + "id=" + id + ", zip=" + zip + ", email=" + email + ", name=" + name + ", surname=" + surname + ", adre\u00e7a=" + adreça + ", city=" + city + ", agemax=" + agemax + ", agemin=" + agemin + ", smoker=" + smoker + ", pet=" + pet + '}';
    } 
}
