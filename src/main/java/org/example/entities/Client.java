package org.example.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Client {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String eMail;

    @NotEmpty(message = "Password should not be empty")
    @Size(min=6, max=15, message = "Password should be between 6 and 15 characters")
    private String password;

    private String name;
    private String status;
    private String role;

    public Client() {}

    public Client(String eMail, String password, String name) {
        this.eMail = eMail;
        this.password = password;
        this.name = name;
    }
    public Client(String eMail, String password, String name, String role, String status) {
        this.eMail = eMail;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;

    }
    public Client(String eMail, String name) {
        this.eMail = eMail;
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

