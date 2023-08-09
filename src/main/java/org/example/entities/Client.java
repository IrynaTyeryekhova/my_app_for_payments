package org.example.entities;

import org.example.services.FieldEquals;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldEquals( field="password", equalsTo="confirmPassword" )
public class Client {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String eMail;

    @NotEmpty(message = "Password should not be empty")
    @Size(min=6, max=15, message = "Password should be between 6 and 15 characters")
    private String password;

    @Size(min=6, max=15, message = "Password should be between 6 and 15 characters")
    private String confirmPassword;

    private String userName;
    private int status;
    private int role;

    public Client() {}

    public Client(String eMail, String password, String userName) {
        this.eMail = eMail;
        this.password = password;
        this.userName = userName;
    }
    public Client(String eMail, String password, String userName, int role, int status) {
        this.eMail = eMail;
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.status = status;

    }
    public Client(String eMail, String userName) {
        this.eMail = eMail;
        this.userName = userName;
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

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

