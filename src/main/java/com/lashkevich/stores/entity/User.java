package com.lashkevich.stores.entity;

import java.time.LocalDate;

public class User {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private LocalDate birthDate;
    private long role;
    private long city;

    public User() {
    }

    public User(long id, String name, String surname, String login, String password, String email, LocalDate birthDate, long role, long city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return true;
        }

        User user = (User) o;

        if (id == user.id && name.equals(user.name) && surname.equals(user.surname) && login.equals(user.login) &&
                password.equals(user.password) && email.equals(user.email) && birthDate.equals(user.birthDate) &&
                role == user.role && city == user.city) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int code = 32;
        code += id;
        code += name.hashCode();
        code += surname.hashCode();
        code += login.hashCode();
        code += password.hashCode();
        code += email.hashCode();
        code += birthDate.hashCode();
        code += role;
        code += city;

        return code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", role=" + role +
                ", city=" + city +
                '}';
    }
}
