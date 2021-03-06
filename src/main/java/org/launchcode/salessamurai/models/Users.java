package org.launchcode.salessamurai.models;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
public class Users {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max=15)
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Product> products;

    public Users() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {return products;}

}

