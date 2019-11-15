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
    @NamedQuery(name = "Tenant.findByName", query = "SELECT c FROM Tenant c WHERE c.name = :name"),
    @NamedQuery(name = "Tenant.findBySurname", query = "SELECT c FROM Tenant c WHERE c.surname = :surname"),
    @NamedQuery(name = "Tenant.findByAge", query = "SELECT c FROM Tenant c WHERE c.age = :age"),
    @NamedQuery(name = "Tenant.findByPet", query = "SELECT c FROM Tenant c WHERE c.pet = :pet"),
    @NamedQuery(name = "Tenant.findBySmoker", query = "SELECT c FROM Tenant c WHERE c.smoker = :smoker"),
    @NamedQuery(name = "Tenant.findByEmail", query = "SELECT c FROM Tenant c WHERE c.email = :email"),
    @NamedQuery(name = "Tenant.findBySex", query = "SELECT c FROM Tenant c WHERE c.sex = :sex")})
public class Tenant implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PET")
    private Boolean pet;
    @Column(name = "SMOKER")
    private Boolean smoker;
    @Size(max = 20)
    @Column(name = "SEX")
    private String sex;
    @Column(name = "AGE")
    private Integer age;

    public Tenant(){
        
    }

    public Tenant(Integer id, String name, String surname, String email, Boolean pet, Boolean smoker, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pet = pet;
        this.smoker = smoker;
        this.sex = sex;
        this.age = age;
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