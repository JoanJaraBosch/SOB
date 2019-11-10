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
@Table(name = "TENANT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tenant.findAll", query = "SELECT c FROM Tenant c"),
    @NamedQuery(name = "Tenant.findById", query = "SELECT c FROM Tenant c WHERE c.id = :id"),
    @NamedQuery(name = "Tenant.findByUsername", query = "SELECT c FROM Tenant c WHERE c.username = :username"),
    @NamedQuery(name = "Tenant.findByName", query = "SELECT c FROM Tenant c WHERE c.name = :name"),
    @NamedQuery(name = "Tenant.findBySurname", query = "SELECT c FROM Tenant c WHERE c.surname = :surname"),
    @NamedQuery(name = "Tenant.findByAge", query = "SELECT c FROM Tenant c WHERE c.age = :age"),
    @NamedQuery(name = "Tenant.findByPet", query = "SELECT c FROM Tenant c WHERE c.pet = :pet"),
    @NamedQuery(name = "Tenant.findBySmoker", query = "SELECT c FROM Tenant c WHERE c.smoker = :smoker"),
    @NamedQuery(name = "Tenant.findByEmail", query = "SELECT c FROM Tenant c WHERE c.email = :email"),
    @NamedQuery(name = "Tenant.findBySex", query = "SELECT c FROM Tenant c WHERE c.sex = :sex")})
public class Tenant implements Serializable{
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password = "";
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username = "";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id=0;
     @Size(max = 20)
    @Column(name = "NAME")
    private String name="";
    @Size(max = 20)
    @Column(name = "SURNAME")
    private String surname="";
    @Size(max = 20)
    @Column(name = "EMAIL")
    private String email="";
    @Column(name = "PET")
    private Boolean pet = false;
    @Column(name = "SMOKER")
    private Boolean smoker = false;
    @Size(max = 20)
    @Column(name = "SEX")
    private String sex ="";
    @Column(name = "AGE")
    private Integer age = 0;

    public Tenant(){
        
    }
    
    public Tenant(Integer id, String username, String password, String name, String surname, String email, Boolean pet, Boolean smoker, Integer age, String sex) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.name=name;
        this.surname = surname;
        this.email=email;
        this.pet=pet;
        this.smoker=smoker;
        this.age=age;
        this.sex=sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
}
