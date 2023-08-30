package org.example.entities;

import org.example.services.FieldEquals;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldEquals( field="password", equalsTo="confirmPassword" )
public class CardAccount {
    @NotEmpty(message = "Card's number should not be empty")
    @Size(min=16, max=16, message = "Card's number should be contain 16 characters")
    private String number;

//    @NotEmpty(message = "Balance should not be empty")
    private double balance;

    @NotEmpty(message = "Validity period should not be empty")
    @Size(min=10, max=10, message = "Enter a valid date in YYYY-MM-DD format")
    private String validityPeriod;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Confirm password should not be empty")
    private String confirmPassword;
    private String statusCard;
    private String client;
    private int id;

    public CardAccount() {}

    public CardAccount(String number, double balance, String validityPeriod) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validityPeriod;
    }

    public CardAccount(String number, double balance, String validityPeriod, String password) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validityPeriod;
        this.password = password;
    }

    public CardAccount(String number, double balance, String validityPeriod, String password, String statusCard, String client) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validityPeriod;
        this.password = password;
        this.statusCard = statusCard;
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(String statusCard) {
        this.statusCard = statusCard;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

