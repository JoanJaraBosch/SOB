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
public class Renter{
    private String name="";
    private String surname="";
    private String address="";
    private String zip="";
    private String state="";
    private String city="";
    private String country="";
    private String email="";
    private String sex ="";
    private int age = 0;

    public Renter(){
        
    }
    
    public Renter(String name, String surname, String address, String zip, String city, String state, String country, String email, String sex, int age) {
        this.name=name;
        this.surname=surname;
        this.address=address;
        this.age=age;
        this.city=city;
        this.email=email;
        this.zip = zip;
        this.country=country;
        this.state=state;
        this.sex=sex;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEdat() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
