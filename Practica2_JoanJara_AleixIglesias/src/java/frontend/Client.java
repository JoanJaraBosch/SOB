/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

/**
 * Classe pel frontend per a registrar-se i fer les peticions.
 * @author Joan
 */
public class Client {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Boolean smoker;
    private Boolean pet;
    private String sex;
    private Integer age;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncryptPassword.encodePassword(password);
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    
    
    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }

    public String getMessage() {

        return "\nFirst Name: " + getName()+ "\n"
                + "Last Name:  " + getSurname()+ "\n"
                + "Username:  "+ getUsername()+"\n"
                + "Password:  "+ getPassword()+"\n"
                + "Email:      " + getEmail() + "\n"
                + "Sex:  "+ getSex()+"\n"
                + "Pet:  "+ String.valueOf(getPet())+"\n"
                + "Smoker:  "+ String.valueOf(getSmoker())+"\n"
                + "Age:  "+ String.valueOf(getAge())+"\n";
    }
}

