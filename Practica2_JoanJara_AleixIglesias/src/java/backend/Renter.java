/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.EncryptPassword;
import java.io.Serializable;
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
    @NamedQuery(name = "Renter.findBySurname", query = "SELECT c FROM Renter c WHERE c.adresa = :adresa"),
    @NamedQuery(name = "Renter.findByEmail", query = "SELECT c FROM Renter c WHERE c.email = :email"),
    @NamedQuery(name = "Renter.findByZip", query = "SELECT c FROM Renter c WHERE c.zip = :zip"),
    @NamedQuery(name = "Renter.findBySex", query = "SELECT c FROM Renter c WHERE c.sex = :sex"),
    @NamedQuery(name = "Renter.findByCity", query = "SELECT c FROM Renter c WHERE c.city = :city"),
    @NamedQuery(name = "Renter.findByAgeMax", query = "SELECT c FROM Renter c WHERE c.agemax = :agemax"),
    @NamedQuery(name = "Renter.findByAgeMin", query = "SELECT c FROM Renter c WHERE c.agemin = :agemin"),
    @NamedQuery(name = "Renter.findByPet", query = "SELECT c FROM Renter c WHERE c.pet = :pet"),
    @NamedQuery(name = "Renter.findByPassword", query = "SELECT c FROM Renter c WHERE c.password = :password"),
    @NamedQuery(name = "Renter.findByUsername", query = "SELECT c FROM Renter c WHERE c.username = :username"),
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
    private String email;
    @Size(max = 20)
    @Column(name = "SEX")
    private String sex;
    @Size(max = 400)
    @Column(name = "NAME")
    private String name;
    @Size(max = 400)
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "ADREÇA")
    private String adresa;
    @Size(max = 30)
    @Column(name = "CITY")
    private String city;
    @Column(name = "AGE_MAX")
    private Integer agemax;
    @Column(name = "AGE_MIN")
    private Integer agemin;
    @Column(name = "SMOKER")
    private Boolean smoker;
    @Column(name = "PET")
    private Boolean pet;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 100)
    @Column(name = "USERNAME")
    private String username;
   
    /**
     *
     */
    public Renter() {
    }

    /**
     *
     * @param id
     * @param zip
     * @param email
     * @param sex
     * @param name
     * @param surname
     * @param adreça
     * @param city
     * @param agemax
     * @param agemin
     * @param smoker
     * @param pet
     */
    public Renter(Integer id, Integer zip, String email, String sex, String name, String surname, String adrsa, String city, Integer agemax, Integer agemin, Boolean smoker, Boolean pet, String username, String password) {
        this.id = id;
        this.zip = zip;
        this.email = email;
        this.sex = sex;
        this.name = name;
        this.surname = surname;
        this.adresa = adresa;
        this.city = city;
        this.agemax = agemax;
        this.agemin = agemin;
        this.smoker = smoker;
        this.pet = pet;
        this.password=password;
        this.username=username;
    }
    
    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Integer getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(Integer zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public Integer getAgemax() {
        return agemax;
    }

    /**
     *
     * @param agemax
     */
    public void setAgemax(Integer agemax) {
        this.agemax = agemax;
    }

    /**
     *
     * @return
     */
    public Integer getAgemin() {
        return agemin;
    }

    /**
     *
     * @param agemin
     */
    public void setAgemin(Integer agemin) {
        this.agemin = agemin;
    }

    /**
     *
     * @return
     */
    public Boolean getSmoker() {
        return smoker;
    }

    /**
     *
     * @param smoker
     */
    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    /**
     *
     * @return
     */
    public Boolean getPet() {
        return pet;
    }

    /**
     *
     * @param pet
     */
    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncryptPassword.encodePassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Integer maxID(List<Renter> renters){
        Integer id=0;
        for(Renter a:renters){
            if(a.getId()>id) id = a.getId();
        }
        return id;
    }
}
